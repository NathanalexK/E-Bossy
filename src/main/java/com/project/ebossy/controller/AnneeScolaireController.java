package com.project.ebossy.controller;


import com.project.ebossy.model.AnneeScolaire;
import com.project.ebossy.model.Ecole;
import com.project.ebossy.service.AnneeScolaireService;
import com.project.ebossy.service.EcoleService;
import com.project.ebossy.service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller

public class AnneeScolaireController {

    private final HttpSession httpSession;
    private final AnneeScolaireService anneeScolaireService;
    private final SessionService sessionService;
    private final EcoleService ecoleService;
    private final View error;

    public AnneeScolaireController(HttpSession httpSession, AnneeScolaireService anneeScolaireService, SessionService sessionService, EcoleService ecoleService, View error) {
        this.httpSession = httpSession;
        this.anneeScolaireService = anneeScolaireService;
        this.sessionService = sessionService;
        this.ecoleService = ecoleService;
        this.error = error;
    }

    @GetMapping("/anneeScolaire/set")
    public String setAnneeScolaire(@RequestParam("id") AnneeScolaire anneeScolaire, HttpServletRequest request) {
        httpSession.setAttribute("anneeScolaire", anneeScolaire);
        return "redirect:"+request.getHeader("Referer");
    }

    @GetMapping("/anneeScolaire/form")
    public ModelAndView form(Model model){
        ModelAndView modelAndView = new ModelAndView("direction/anneeScolaire/form");
        modelAndView.addObject("error", model.getAttribute("error"));
        return modelAndView;
    }

    @PostMapping("/anneeScolaire/save")
    public String onSaveAnneeScolaire(
            @RequestParam("nom") String nom,
            @RequestParam("dateDebut") LocalDate dateDebut,
            @RequestParam("dateFin") LocalDate dateFin,
            RedirectAttributes redirectAttributes
    ){
        if(dateDebut.isAfter(dateFin)){
            redirectAttributes.addFlashAttribute("error", "Les dates sont incorrectes");
            return "redirect:/anneeScolaire/form";
        }
        AnneeScolaire as = new AnneeScolaire();
        Ecole ecole = sessionService.getEcole();
        as.setNom(nom);
        as.setDateDebut(dateDebut);
        as.setDateFin(dateFin);
        as.setIdEcole(ecole);
        AnneeScolaire saved = anneeScolaireService.save(as);
        ecole.setAnneeScolaire(saved);
        ecoleService.save(ecole);
        httpSession.setAttribute("anneeScolaire", as);

        return "redirect:/calendrier/form";
    }
}
