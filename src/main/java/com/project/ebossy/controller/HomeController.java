package com.project.ebossy.controller;


import com.project.ebossy.model.AnneeScolaire;
import com.project.ebossy.model.Ecole;
import com.project.ebossy.model.PeriodeNote;
import com.project.ebossy.model.Professeur;
import com.project.ebossy.service.*;
import com.project.ebossy.util.Role;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {


    private final HttpSession httpSession;
    private final EcoleService ecoleService;
    private final MailService mailService;
    private final ProfesseurService professeurService;
    private final LayoutService layoutService;
    private final SessionService sessionService;
    private final DatabaseService databaseService;
    private final PeriodeNoteService periodeNoteService;
    private final MatiereService matiereService;

    public HomeController(HttpSession httpSession, EcoleService ecoleService, MailService mailService, ProfesseurService professeurService, LayoutService layoutService, SessionService sessionService, DatabaseService databaseService, PeriodeNoteService periodeNoteService, MatiereService matiereService) {
        this.httpSession = httpSession;
        this.ecoleService = ecoleService;
        this.mailService = mailService;
        this.professeurService = professeurService;
        this.layoutService = layoutService;
        this.sessionService = sessionService;
        this.databaseService = databaseService;
        this.periodeNoteService = periodeNoteService;
        this.matiereService = matiereService;
    }

    @RequestMapping("/index")
    public ModelAndView index(){
        if(httpSession.getAttribute("user") != null){
            return new ModelAndView("redirect:/dashboard");
        }
//        if(httpSession.getAttribute("role") != null){
//            return new ModelAndView("login/connexion");
//        }
        return new ModelAndView("login/espace");
//        httpSession.setAttribute("role", "professeur");
//        Ecole myEcole = ecoleService.getEcoleById(2).get();
//        Professeur professeur = professeurService.findByIdentifiant("ranary@lcsmi.itaosy");
//        httpSession.setAttribute("role", Role.PROFESSEUR);
//        httpSession.setAttribute("professeur", professeur);
//        httpSession.setAttribute("anneeScolaire", myEcole.getAnneeScolaire());
//        httpSession.setAttribute("ecole", myEcole);
//        new Thread(() -> {
//            mailService.sendEmail("Kevin", "nathanalekskevin@gmail.com", "Hellos", "Hello it works");
//        }).start();
//        return "redirect:/niveau/form";
    }

    @RequestMapping("/erreur")
    public ModelAndView error(){
        ModelAndView modelAndView = layoutService.getLayout();
        modelAndView.addObject("page", "erreur/autorisation");

        return modelAndView;
    }


    @RequestMapping(value = "/dashboard")
    public ModelAndView dirigeantDashboard(
            @RequestParam(name = "anneeScolaire", required = false)AnneeScolaire anneeScolaire
        ){
        ModelAndView modelAndView = layoutService.getLayout();
        modelAndView.addObject("page", "direction/dashboard");

        AnneeScolaire myAnneeScolaire = anneeScolaire != null ? anneeScolaire : sessionService.getAnneeScolaire();
        List<PeriodeNote> periodeNoteList = periodeNoteService.findAllByAnneeScolaire(myAnneeScolaire);
        modelAndView.addObject("moyenneGenerale", databaseService.getMoyenneAnneeScolaire(myAnneeScolaire));
        modelAndView.addObject("periodeNoteList", periodeNoteList);
        modelAndView.addObject("moyennesGenerales", databaseService.getDashboardMoyenneParPeriode(myAnneeScolaire));
        modelAndView.addObject("nombresEleves", databaseService.getNombresElevesParAnneeScolaire(myAnneeScolaire));
        modelAndView.addObject("sexes", databaseService.getSexeParAnneeScolaire(myAnneeScolaire));
        modelAndView.addObject("matiereMoyenne", databaseService.getMoyenneMatiereParAnneeScolaire(myAnneeScolaire));
        modelAndView.addObject("matiereList", matiereService.findAllByAnneeScolaire(myAnneeScolaire));
        return modelAndView;
    }
}
