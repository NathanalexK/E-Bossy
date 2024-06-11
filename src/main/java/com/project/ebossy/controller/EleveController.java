package com.project.ebossy.controller;

import com.project.ebossy.model.*;
import com.project.ebossy.repository.SexeRepository;
import com.project.ebossy.service.EcoleService;
import com.project.ebossy.service.EleveService;

import com.project.ebossy.service.NiveauService;
import com.project.ebossy.service.TuteurService;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/eleve")
public class EleveController {
    private final EleveService eleveService;
    private final HttpSession httpSession;
    private final EcoleService ecoleService;
    private final SexeRepository sexeRepository;
    private final NiveauService niveauService;
    private final TuteurService tuteurService;

    public EleveController(EcoleService ecoleService, HttpSession httpSession, EleveService eleveService, SexeRepository sexeRepository, NiveauService niveauService, TuteurService tuteurService) {
        this.eleveService = eleveService;
        this.httpSession = httpSession;
        this.ecoleService = ecoleService;
        this.sexeRepository = sexeRepository;
        this.niveauService = niveauService;
        this.tuteurService = tuteurService;
    }

    @GetMapping("/form")
    public ModelAndView form(Model model){
        ModelAndView modelAndView = new ModelAndView("direction/layout");
        modelAndView.addObject("page", "direction/eleve/form");

        Ecole myEcole = ((Ecole) httpSession.getAttribute("ecole"));

        modelAndView.addObject("sexeList", sexeRepository.findAll());
        modelAndView.addObject("niveauList", niveauService.findAll(myEcole.getId()));
        modelAndView.addObject("erreur", model.getAttribute("erreur"));
        modelAndView.addObject("success", model.getAttribute("success"));

        return modelAndView;
    }

    @PostMapping("/save")
    public String onSave(
            @RequestParam("nom") String nom,
            @RequestParam("prenom") String prenom,
            @RequestParam("idSexe") Sexe idSexe,
            @RequestParam("date") LocalDate date,
            @RequestParam("email") String email,
            @RequestParam("mdp") String mdp,
            @RequestParam("idNiveau") Niveau niveau,
            @RequestParam("identifiant") String identifiant,
            RedirectAttributes redirectAttributes
    ){
        Map<String, String> errors = new HashMap<>();
        Tuteur tuteur = tuteurService.findByEmail(email);
        if(tuteur == null){
            redirectAttributes.addAttribute("erreur", "Aucun tutueur correspond à: " + email);
        }
        Eleve eleve = new Eleve();
        eleve.setNom(nom);
        eleve.setPrenom(prenom);
        eleve.setIdSexe(idSexe);
        eleve.setDateNaissance(date);
        eleve.setIdNiveau(niveau);
        eleve.setIdTuteur(tuteur);
        eleve.setIdentifiant(identifiant);
        eleve.setMdp(mdp);
        eleveService.save(eleve);
        redirectAttributes.addAttribute("success", nom + " " + prenom + "a été bien inscris");
        return "redirect:/eleve/form";
    }

    @PostMapping("/{id}")
    public String onUpdate(
    ){

        return "redirect:/eleve/form";
    }
}
