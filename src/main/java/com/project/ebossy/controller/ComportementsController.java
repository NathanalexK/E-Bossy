package com.project.ebossy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.project.ebossy.model.Comportements;
import com.project.ebossy.model.Classe;
import com.project.ebossy.model.Ecole;
import com.project.ebossy.service.ClasseService;
import com.project.ebossy.service.ComportementsService;
import com.project.ebossy.service.LayoutService;
import com.project.ebossy.service.RoleService;
import com.project.ebossy.util.Role;
import com.project.ebossy.view.ComportementsEleve;
import com.project.ebossy.service.ComportementsEleveService;

import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/comportements")
@CrossOrigin("*")
public class ComportementsController {
    private final HttpSession httpSession;
    private final ComportementsService comportementsService;
    private final ClasseService classeService;
    private final ComportementsEleveService comportementsEleveService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private LayoutService layoutService;

    @Autowired
    public ComportementsController(HttpSession httpSession, ComportementsService comportementsService, ClasseService classeService, ComportementsEleveService comportementsEleveService) {
        this.httpSession = httpSession;
        this.comportementsService = comportementsService;
        this.classeService = classeService;
        this.comportementsEleveService = comportementsEleveService;
    }

    @GetMapping("/form")
    public ModelAndView comportementsForm() {
        roleService.allowedRoles(Role.PROFESSEUR, Role.DIRECTEUR);

        ModelAndView modelAndView = layoutService.getLayout();
        Ecole ecole = (Ecole) httpSession.getAttribute("ecole");
        modelAndView.addObject("page", "direction/comportements/form");
        modelAndView.addObject("comportementsList", comportementsService.findAll());

        if (ecole != null) {
            List<Classe> classeList = classeService.findAll(ecole);
            modelAndView.addObject("classeList", classeList);
        }

        return modelAndView;
    }
@GetMapping("/list")
public String showComportementsList(Model model) {
    Ecole ecole = (Ecole) httpSession.getAttribute("ecole");
    List<Classe> classeList = classeService.findAll(ecole);
    List<Comportements> comportementsList = comportementsService.findAll();
    List<ComportementsEleve> eleveList = comportementsEleveService.findAll();
    
    // Vérifiez si la liste est null ou vide et ajoutez une logique de gestion
    if (eleveList == null || eleveList.isEmpty()) {
        eleveList = new ArrayList<>(); // Initialisez à une liste vide
    }
    
    model.addAttribute("classeList", classeList);
    model.addAttribute("comportementsList", comportementsList);
    model.addAttribute("eleveList", eleveList);
    model.addAttribute("selectedClasse", null);
    
    return "direction/comportements/list";
}

    @PostMapping("/save")
    public String ComportementsSave(@RequestParam("nom") String nom) {
        Comportements comportements = new Comportements();
        comportements.setNom(nom);
        comportementsService.save(comportements);
        return "redirect:/comportements/form";
    }

    @PostMapping("/save1")
    public String ComportementsEleveSave(@RequestParam("idClasse") int idClasse) {
        // Ajouter ici la logique de sauvegarde si nécessaire
        return "redirect:/comportements/form";
    }

    @PostMapping("/delete")
    public String professeurDelete(@RequestParam("idComportement") Integer id) {
        comportementsService.deleteById(id);
        return "redirect:/comportements/form";
    }
}
