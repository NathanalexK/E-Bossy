package com.project.ebossy.controller;

import com.project.ebossy.model.*;
import com.project.ebossy.service.LayoutService;
import com.project.ebossy.service.MatiereNiveauService;
import com.project.ebossy.service.MatiereService;
import com.project.ebossy.service.NiveauService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
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
    private final MatiereNiveauService matiereNiveauService;
    private final LayoutService layoutService;

    public MatiereController(NiveauService niveauService, HttpSession httpSession, MatiereService matiereService, MatiereNiveauService matiereNiveauService, LayoutService layoutService) {
        this.niveauService = niveauService;
        this.httpSession = httpSession;
        this.matiereService = matiereService;
        this.matiereNiveauService = matiereNiveauService;
        this.layoutService = layoutService;
    }

    @GetMapping("/form")
    public ModelAndView form(){
        ModelAndView modelAndView = layoutService.getLayout();
        modelAndView.addObject("page", "direction/matiere/form");

        Ecole myEcole = ((Ecole) httpSession.getAttribute("ecole"));
        AnneeScolaire anneeScolaire = ((AnneeScolaire) httpSession.getAttribute("anneeScolaire"));

        modelAndView.addObject("niveauList", niveauService.findAllByAnneeScolaire(anneeScolaire));
        modelAndView.addObject("matiereList", matiereService.findAllByAnneeScolaire(anneeScolaire));

        return modelAndView;
    }

    @PostMapping("/save")
    @Transactional
    public String onSave(
            @RequestParam("nom") String nom,
            @RequestParam("idNiveau[]") List<Integer> idNiveauList,
            @RequestParam("coefficient[]") List<Integer> coefficientList,
            @RequestParam("horaire[]") List<Integer> horaireList
    ){
        Matiere matiere = new Matiere();
        matiere.setNomMatiere(nom);
        matiere.setIdEcole(((Ecole) httpSession.getAttribute("ecole")));
        Matiere savedMatiere = matiereService.save(matiere);

        for (int i = 0; i < idNiveauList.size(); i++) {
            MatiereNiveau mn = new MatiereNiveau();
            mn.setIdMatiere(savedMatiere);

            Niveau niveau = niveauService.findById(idNiveauList.get(i));
            mn.setIdNiveau(niveau);
            mn.setCoefficient(coefficientList.get(i));
            mn.setVolumeHoraire(horaireList.get(i));
            matiereNiveauService.save(mn);
        }

        return "redirect:/matiere/form";
    }

    @PostMapping("/{id}")
    public String onUpdate(
            @RequestParam("id") Integer id,
            @RequestParam("nom") String nom,
            @RequestParam("idNiveau") Integer idNiveau,
            @RequestParam("coefficient") Integer coefficient
    ){
//        Matiere matiere = new Matiere();
//        matiere.setNomMatiere(nom);
//        matiere.setCoefficient(coefficient);
//        Niveau niveau = niveauService.findById(idNiveau);
//        matiere.setIdNiveau(niveau);
//        matiere.setIdEcole(((Ecole) httpSession.getAttribute("ecole")));
//        matiereService.update(matiere);

        return "redirect:/matiere/form";
    }
}
