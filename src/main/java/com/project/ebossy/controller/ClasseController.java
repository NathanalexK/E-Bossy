package com.project.ebossy.controller;


import com.project.ebossy.model.Classe;
import com.project.ebossy.model.Ecole;
import com.project.ebossy.model.Professeur;
import com.project.ebossy.model.Salle;
import com.project.ebossy.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/classe")
public class ClasseController {

    private final HttpSession httpSession;
    private final SalleService salleService;
    private final NiveauService niveauService;
    private final ClasseService classeService;
    private final ProfesseurService professeurService;
    private final LayoutService layoutService;

    public ClasseController(HttpSession httpSession, SalleService salleService, NiveauService niveauService, ClasseService classeService, ProfesseurService professeurService, LayoutService layoutService) {
        this.httpSession = httpSession;
        this.salleService = salleService;
        this.niveauService = niveauService;
        this.classeService = classeService;
        this.professeurService = professeurService;
        this.layoutService = layoutService;
    }

    @GetMapping("/form")
    public ModelAndView classeForm(){
        ModelAndView modelAndView = layoutService.getLayout();
        modelAndView.addObject("page", "direction/classe/form");

        Ecole myEcole = ((Ecole) httpSession.getAttribute("ecole"));
        modelAndView.addObject("salleList", salleService.getSalleDisponibles(myEcole));
        modelAndView.addObject("niveauList", niveauService.findAll(myEcole.getId()));
        modelAndView.addObject("classeList", classeService.findAll(myEcole));
        modelAndView.addObject("professeurList", professeurService.findAllByEcole(myEcole));

        return modelAndView;
    }

    @PostMapping("/save")
    public String onSave(
            @RequestParam("nomClasse") String nomClasse,
            @RequestParam("idNiveau") int idNiveau,
            @RequestParam("idSalle") int idSalle,
            @RequestParam("idProf") Professeur idProf
    ){
        Salle salle = salleService.findById(idSalle);

        if(classeService.isSalleOccuped(salle)) {
            return "redirect:/classe/form";
        }

        Classe classe = new Classe();
        classe.setNomClasse(nomClasse);
        classe.setIdNiveau(niveauService.findById(idNiveau));
        classe.setIdSalle(salle);
        classe.setIdEcole(((Ecole) httpSession.getAttribute("ecole")));
        classe.setIdTitulaire(idProf);
        classeService.save(classe);
        return "redirect:/classe/form";
    }

    @PostMapping("/delete")
    public String onDelete(@RequestParam("idClasse") int idClasse) {
        classeService.deleteById(idClasse);
        return "redirect:/classe/form";
    }

}
