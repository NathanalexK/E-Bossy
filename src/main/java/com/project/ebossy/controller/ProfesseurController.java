package com.project.ebossy.controller;


import com.project.ebossy.model.Ecole;
import com.project.ebossy.model.Professeur;
import com.project.ebossy.model.Sexe;
import com.project.ebossy.service.EcoleService;
import com.project.ebossy.service.NiveauService;
import com.project.ebossy.service.ProfesseurService;
import com.project.ebossy.service.SexeService;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@Controller
@RequestMapping("/professeur")
public class ProfesseurController {
    
    private final HttpSession httpSession;
    private final ProfesseurService professeurService;
    private final EcoleService ecoleService;
    private final SexeService sexeService;

    public ProfesseurController(HttpSession httpSession, ProfesseurService professeurService, EcoleService ecoleService, SexeService sexeService) {
        this.httpSession = httpSession;
        this.professeurService = professeurService;
        this.ecoleService = ecoleService;
        this.sexeService = sexeService;
    }

    @GetMapping("/form")
    public ModelAndView professeurForm() {
        ModelAndView modelAndView = new ModelAndView("direction/layout");

        Ecole ecole = (Ecole) httpSession.getAttribute("ecole");

        modelAndView.addObject("page", "direction/professeur/form");
        modelAndView.addObject("professeurList" , professeurService.findAll(ecole.getId()));
        modelAndView.addObject("sexeList" , sexeService.findAll());
        
        return modelAndView;
    }

    @PostMapping("/save")
    public String professeurSave(
        @RequestParam("nom") String nom,
        @RequestParam("prenom") String prenom,
        @RequestParam("dateNaissance") LocalDate dateNaissance,
        @RequestParam("adresse") String adresse,
        @RequestParam("contact") String contact,
        @RequestParam("email") String email,
        @RequestParam("mdp") String mdp,
        @RequestParam("idSexe") Sexe idSexe        
    ) { 
        Professeur professeur = new Professeur();
        Ecole idEcole = (Ecole) httpSession.getAttribute("ecole");
        professeur.setIdEcole(idEcole);
        professeur.setNom(nom);
        professeur.setPrenom(prenom);
        professeur.setDateNaissance(dateNaissance);
        professeur.setConctact(contact);
        professeur.setEmail(email);
        professeur.setAdresse(adresse);
        professeur.setMdp(mdp);
        professeur.setIdSexe(idSexe);
        professeurService.save(professeur);
        return "redirect:/professeur/form";
    }

    @PostMapping("/delete")
    public String professeurDelete(@RequestParam("idProfesseur") Integer id) {
        professeurService.deleteById(id);
        return "redirect:/professeur/form";
    }
}
