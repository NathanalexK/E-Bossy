package com.project.ebossy.controller;

import com.project.ebossy.model.Ecole;
import com.project.ebossy.model.Professeur;
import com.project.ebossy.model.Secretaire;
import com.project.ebossy.model.Sexe;
import com.project.ebossy.service.EcoleService;
import com.project.ebossy.service.ProfesseurService;
import com.project.ebossy.service.SecretaireService;
import com.project.ebossy.service.SexeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@Controller
@RequestMapping("/secretaire")
public class SecretaireController {
    private final HttpSession httpSession;
    private final SecretaireService secretaireService;
    private final EcoleService ecoleService;
    private final SexeService sexeService;

    public SecretaireController(HttpSession httpSession, SecretaireService secretaireService, EcoleService ecoleService, SexeService sexeService) {
        this.httpSession = httpSession;
        this.secretaireService = secretaireService;
        this.ecoleService = ecoleService;
        this.sexeService = sexeService;
    }

    @GetMapping("/form")
    public ModelAndView secretaireForm() {
        ModelAndView modelAndView = new ModelAndView("direction/layout");

        Ecole ecole = (Ecole) httpSession.getAttribute("ecole");

        modelAndView.addObject("page", "direction/secretaire/form");
        modelAndView.addObject("secretaireList" , secretaireService.findAll(ecole.getId()));
        modelAndView.addObject("sexeList" , sexeService.findAll());

        return modelAndView;
    }

    @PostMapping("/save")
    public String secretaireSave(
            @RequestParam("nom") String nom,
            @RequestParam("prenom") String prenom,
            @RequestParam("dateNaissance") LocalDate dateNaissance,
            @RequestParam("adresse") String adresse,
            @RequestParam("contact") String contact,
            @RequestParam("email") String email,
            @RequestParam("mdp") String mdp,
            @RequestParam("idSexe") Sexe idSexe
    ) {
        Secretaire secretaire = new Secretaire();
        Ecole idEcole = (Ecole) httpSession.getAttribute("ecole");
        secretaire.setIdEcole(idEcole);
        secretaire.setNom(nom);
        secretaire.setPrenom(prenom);
        secretaire.setDateNaissance(dateNaissance);
        secretaire.setContact(contact);
        secretaire.setEmail(email);
        secretaire.setAdresse(adresse);
        secretaire.setMdp(mdp);
        secretaire.setIdSexe(idSexe);
        secretaireService.save(secretaire);
        return "redirect:/secretaire/form";
    }

    @PostMapping("/delete")
    public String secretaireDelete(@RequestParam("idSecretaire") Integer id) {
        secretaireService.deleteById(id);
        return "redirect:/secretaire/form";
    }
}
