package com.project.ebossy.controller;


import com.project.ebossy.model.Ecole;
import com.project.ebossy.model.Professeur;
import com.project.ebossy.model.Sexe;
import com.project.ebossy.repository.SexeRepository;
import com.project.ebossy.service.ProfesseurService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Map;

@Controller
@RequestMapping("/prof")
public class ProfesseurController {


    private final SexeRepository sexeRepository;
    private final ProfesseurService professeurService;
    private final HttpSession httpSession;

    public ProfesseurController(SexeRepository sexeRepository, ProfesseurService professeurService, HttpSession httpSession) {
        this.sexeRepository = sexeRepository;
        this.professeurService = professeurService;
        this.httpSession = httpSession;
    }

    @GetMapping("/form")
    public ModelAndView form(Model model){
        ModelAndView modelAndView = new ModelAndView("direction/layout");
        modelAndView.addObject("page", "direction/prof/form");

        modelAndView.addObject("erreurs", model.getAttribute("erreurs"));
        modelAndView.addObject("success", model.getAttribute("success"));
        modelAndView.addObject("professeur", model.getAttribute("professeur"));

        modelAndView.addObject("sexeList", sexeRepository.findAll());

        return modelAndView;
    }

    @PostMapping("/save")
    public String save(
            @RequestParam("nom") String nom,
            @RequestParam("prenom") String prenom,
            @RequestParam("dateNaissance") LocalDate dateNaissance,
            @RequestParam("adresse") String adresse,
            @RequestParam("contact") String contact,
            @RequestParam("email") String email,
            @RequestParam("mdp") String mdp,
            @RequestParam("idSexe") Sexe sexe,
            @RequestParam("debutCarriere") LocalDate debutCarriere,
            @RequestParam("identifiant") String identifiant,
            RedirectAttributes redirectAttributes
    ){
        Professeur professeur = new Professeur();
        professeur.setNom(nom);
        professeur.setPrenom(prenom);
        professeur.setDateNaissance(dateNaissance);
        professeur.setAdresse(adresse);
        professeur.setEmail(email);
        professeur.setConctact(contact);
        professeur.setIdSexe(sexe);
        professeur.setDebutCarriere(debutCarriere);
        professeur.setIdentifiant(identifiant);
        professeur.setMdp(mdp);
        Map<String, String> erreurs = professeurService.getErreurs(professeur);

        if(erreurs.isEmpty()){
            Ecole myEcole = (Ecole) httpSession.getAttribute("ecole");
            professeur.setIdEcole(myEcole);
            professeurService.save(professeur);
            redirectAttributes.addFlashAttribute("success", "Le professeur " + professeur.getNom() + " " + professeur.getPrenom() + " a été bien ajouté");
        }

        else {
            redirectAttributes.addFlashAttribute("erreurs", erreurs);
            redirectAttributes.addFlashAttribute("professeur", professeur);
        }

        return "redirect:/prof/form";
    }
}
