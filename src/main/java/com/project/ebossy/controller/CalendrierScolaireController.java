package com.project.ebossy.controller;


import com.project.ebossy.model.AnneeScolaire;
import com.project.ebossy.model.Ecole;
import com.project.ebossy.model.EvenementScolaire;
import com.project.ebossy.model.PeriodeNote;
import com.project.ebossy.service.*;
import com.project.ebossy.util.Role;
import com.project.ebossy.view.CalendrierScolaire;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/calendrier")
public class CalendrierScolaireController {

    private final HttpSession httpSession;
    private final AnneeScolaireService anneeScolaireService;
    private final EvenementScolaireService evenementScolaireService;
    private final PeriodeNoteService periodeNoteService;
    private final CalendrierScolaireService calendrierScolaireService;
    private final RoleService roleService;
    private final LayoutService layoutService;
    private final SessionService sessionService;

    public CalendrierScolaireController(HttpSession httpSession, AnneeScolaireService anneeScolaireService, EvenementScolaireService evenementScolaireService, PeriodeNoteService periodeNoteService, CalendrierScolaireService calendrierScolaireService, RoleService roleService, LayoutService layoutService, SessionService sessionService) {
        this.httpSession = httpSession;
        this.anneeScolaireService = anneeScolaireService;
        this.evenementScolaireService = evenementScolaireService;
        this.periodeNoteService = periodeNoteService;
        this.calendrierScolaireService = calendrierScolaireService;
        this.roleService = roleService;
        this.layoutService = layoutService;
        this.sessionService = sessionService;
    }

    @GetMapping("/form")
    public ModelAndView calendrierForm() {
        ModelAndView modelAndView = layoutService.getLayout();
        modelAndView.addObject("page", "direction/calendrier/form");
        Ecole myEcole = ((Ecole) httpSession.getAttribute("ecole"));
        AnneeScolaire anneeScolaire = ((AnneeScolaire) httpSession.getAttribute("anneeScolaire"));

        Map<Integer, List<CalendrierScolaire>> eventByStatus = calendrierScolaireService.getCalendrierScolaireGrouppedByStatus(anneeScolaire);

        modelAndView.addObject("readonly", roleService.canAccess(Role.PROFESSEUR, Role.TUTEUR, Role.ELEVE, Role.PARENT, Role.SECRETAIRE));
        modelAndView.addObject("avenir", eventByStatus.get(1));
        modelAndView.addObject("encours", eventByStatus.get(2));
        modelAndView.addObject("fini", eventByStatus.get(3));

        return modelAndView;
    }


    @PostMapping("/save")
    public String onSaveAnneeScolaire(
            @RequestParam("nom") String nom,
            @RequestParam("dateDebut") LocalDate dateDebut,
            @RequestParam("dateFin") LocalDate dateFin
    ){
        if(dateDebut.isAfter(dateFin)) return "redirect:/calendrier/form";


        AnneeScolaire as = new AnneeScolaire();
        as.setNom(nom);
        as.setDateDebut(dateDebut);
        as.setDateFin(dateFin);
        as.setIdEcole(((Ecole) httpSession.getAttribute("ecole")));
        anneeScolaireService.save(as);
        return "redirect:/calendrier/form";
    }
//
    @PostMapping("/evenement/save")
    public String onSaveEvenement(
            @RequestParam("nom") String nom,
            @RequestParam("dateDebut") LocalDateTime dateDebut,
            @RequestParam("dateFin") LocalDateTime dateFin,
            @RequestParam("description") String description
    ) {
        if(dateDebut.isAfter(dateFin)) return "redirect:/calendrier/form";

        EvenementScolaire es = new EvenementScolaire();
        es.setLibelle(nom);
        es.setDateDebut(dateDebut);
        es.setDateFin(dateFin);
        es.setDescription(description);
        Ecole myEcole = (Ecole) httpSession.getAttribute("ecole");
        es.setIdEcole(myEcole);
        es.setIdAnneeScolaire(sessionService.getAnneeScolaire());
        evenementScolaireService.save(es);

        return "redirect:/calendrier/form";
    }

    @PostMapping("/examen/save")
    public String onSaveExamen(
            @RequestParam("nom") String nom,
            @RequestParam("dateDebut") LocalDate dateDebut,
            @RequestParam("dateFin") LocalDate dateFin
    ) {
        if(dateDebut.isAfter(dateFin)) return "redirect:/calendrier/form";

        Ecole myEcole = (Ecole) httpSession.getAttribute("ecole");
        PeriodeNote pn = new PeriodeNote();
        pn.setNomPeriode(nom);
        pn.setDateDebut(dateDebut);
        pn.setDateFin(dateFin);
        pn.setIdEcole(myEcole);
        pn.setIdAnneeScolaire(sessionService.getAnneeScolaire());
        periodeNoteService.save(pn);
        return "redirect:/calendrier/form";
    }

    @PostMapping("/delete")
    public String onDeleteEvenement(
            @RequestParam("typeEvent") int typeEvent,
            @RequestParam("idEvent") int idEvent
    ) {
        if(typeEvent == 0) {
            evenementScolaireService.deleteById(idEvent);
        }
        else {
            periodeNoteService.deleteById(idEvent);
        }
        return "redirect:/calendrier/form";
    }
}
