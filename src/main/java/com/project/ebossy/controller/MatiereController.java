package com.project.ebossy.controller;

import com.project.ebossy.model.Ecole;
import com.project.ebossy.model.Matiere;
import com.project.ebossy.model.Niveau;
import com.project.ebossy.service.MatiereService;
import com.project.ebossy.service.NiveauService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/matiere")
public class MatiereController {


    private final NiveauService niveauService;
    private final HttpSession httpSession;
    private final MatiereService matiereService;

    public MatiereController(NiveauService niveauService, HttpSession httpSession, MatiereService matiereService) {
        this.niveauService = niveauService;
        this.httpSession = httpSession;
        this.matiereService = matiereService;
    }

    @GetMapping("/form")
    public ModelAndView form(){
        ModelAndView modelAndView = new ModelAndView("direction/layout");
        modelAndView.addObject("page", "direction/matiere/form");

        Ecole myEcole = ((Ecole) httpSession.getAttribute("ecole"));

        modelAndView.addObject("niveauList", niveauService.findAll(myEcole.getId()));

        return modelAndView;
    }

    @PostMapping("/save")
    public String onSave(
            @RequestParam("nom") String nom,
            @RequestParam("idNiveau[]") List<Integer> idNiveauList,
            @RequestParam("coefficient[]") List<Integer> coefficientList
    ){
        for (int i = 0; i < idNiveauList.size(); i++) {
            Matiere matiere = new Matiere();
            matiere.setNomMatiere(nom);
            matiere.setCoefficient(coefficientList.get(i));
            Niveau niveau = niveauService.findById(idNiveauList.get(i));
            matiere.setIdNiveau(niveau);
            matiere.setIdEcole(((Ecole) httpSession.getAttribute("ecole")));
            matiereService.save(matiere);
        }

        return "redirect:/matiere/form";
    }
}
