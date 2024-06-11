package com.project.ebossy.controller;

import com.project.ebossy.model.Sexe;
import com.project.ebossy.model.Tuteur;
import com.project.ebossy.repository.SexeRepository;
import com.project.ebossy.repository.TuteurRepository;
import com.project.ebossy.service.TuteurService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/tuteur")
public class TuteurController {

    private final SexeRepository sexeRepository;
    private final TuteurRepository tuteurRepository;
    private final TuteurService tuteurService;

    public TuteurController(SexeRepository sexeRepository,
                            TuteurRepository tuteurRepository, TuteurService tuteurService) {
        this.sexeRepository = sexeRepository;
        this.tuteurRepository = tuteurRepository;
        this.tuteurService = tuteurService;
    }

    @GetMapping("/inscription/form")
    public ModelAndView loginForm(){
        ModelAndView modelAndView = new ModelAndView("tuteur/connexion/inscription");
        modelAndView.addObject("sexeList", sexeRepository.findAll());
        return modelAndView;
    }

    @PostMapping("/inscription/save")
    public String inscription(
            @RequestParam("nom") String nom,
            @RequestParam("prenom") String prenom,
            @RequestParam("date") LocalDate date,
            @RequestParam("adresse") String adresse,
            @RequestParam("contact") String contact,
            @RequestParam("email") String email,
            @RequestParam("mdp") String mdp,
            @RequestParam("sexe") Sexe sexe,
            RedirectAttributes redirectAttributes
    ) {
        Tuteur tuteur = new Tuteur();
        tuteur.setNom(nom);
        tuteur.setPrenom(prenom);
        tuteur.setDateNaissance(date);
        tuteur.setAdresse(adresse);
        tuteur.setConctact(contact);
        tuteur.setEmail(email);
        tuteur.setMdp(mdp);
        tuteur.setIdSexe(sexe);

        Map<String, String> erreurs = tuteurService.getErreurs(tuteur);
        if(!erreurs.isEmpty()){
            redirectAttributes.addFlashAttribute("erreurs", erreurs);
        }
        else {
            tuteurService.save(tuteur);
            redirectAttributes.addFlashAttribute("success", nom + " " + prenom + "a été bien inscris");
        }

        return "redirect:/tuteur/inscription/form";
    }
}
