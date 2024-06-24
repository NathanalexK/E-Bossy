package com.project.ebossy.controller;


import com.project.ebossy.model.AnneeScolaire;
import com.project.ebossy.model.Ecole;
import com.project.ebossy.model.Salle;
import com.project.ebossy.service.DatabaseService;
import com.project.ebossy.service.LayoutService;
import com.project.ebossy.service.SalleService;
import com.project.ebossy.service.SessionService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/salle")
public class SalleController {


    private final SalleService salleService;
    private final HttpSession httpSession;
    private final DatabaseService databaseService;
    private final LayoutService layoutService;
    private final SessionService sessionService;

    public SalleController(SalleService salleService, HttpSession httpSession, DatabaseService databaseService, LayoutService layoutService, SessionService sessionService) {
        this.salleService = salleService;
        this.httpSession = httpSession;
        this.databaseService = databaseService;
        this.layoutService = layoutService;
        this.sessionService = sessionService;
    }

    @GetMapping("/form")
    public ModelAndView salleForm() {
        ModelAndView modelAndView = layoutService.getLayout();
        modelAndView.addObject("page", "direction/salle/form");
        Ecole myEcole = ((Ecole) httpSession.getAttribute("ecole"));
        AnneeScolaire anneeScolaire = sessionService.getAnneeScolaire();
        modelAndView.addObject("salleList", salleService.findAllByAnneeScolaire(anneeScolaire));
        modelAndView.addObject("capacite", databaseService.getCapaciteSalleByEcole(anneeScolaire));
        modelAndView.addObject("nombre", databaseService.getNombreSalleByEcole(anneeScolaire));
//        modelAndView.addObject("salleList", salleService.getSalleDisponibles(((Ecole) httpSession.getAttribute("ecole"))));
        return modelAndView;
    }

    @PostMapping("/save")
    public String onSave(
            @RequestParam("numero") String numero,
            @RequestParam("capacite") int capacite
    ) {
        Salle salle = new Salle();
        salle.setNumero(numero);
        salle.setCapacite(capacite);
        salle.setIdEcole(((Ecole) httpSession.getAttribute("ecole")));
        salle.setAnneeScolaire(sessionService.getAnneeScolaire());
        salleService.save(salle);
        return "redirect:/salle/form";
    }

    @PostMapping("/delete")
    public String onDelete(@RequestParam("idSalle") int idSalle) {
        salleService.delete(idSalle);
        return "redirect:/salle/form";

    }

    @PostMapping("/update")
    @Transactional
    public String onUpdate(
            @RequestParam("idSalle[]") List<Integer> idSalles,
            @RequestParam("numero[]") List<String> numeros,
            @RequestParam("capacite[]") List<Integer> capacites
    ) {
       for(int i = 0; i < idSalles.size(); i++) {
           Salle salle = salleService.findById(idSalles.get(i));
           salle.setNumero(numeros.get(i));
           salle.setCapacite(capacites.get(i));
           salleService.save(salle);
       }

       return "redirect:/salle/form";
    }
}
