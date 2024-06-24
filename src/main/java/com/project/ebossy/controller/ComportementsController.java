package com.project.ebossy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.project.ebossy.model.Comportements;
import com.project.ebossy.model.Classe;
import com.project.ebossy.model.Ecole;
import com.project.ebossy.model.Eleve;
import com.project.ebossy.service.ClasseService;
import com.project.ebossy.service.ComportementsService;
import com.project.ebossy.service.EleveService;
import com.project.ebossy.service.LayoutService;
import com.project.ebossy.service.RoleService;
import com.project.ebossy.util.Role;
import com.project.ebossy.view.ComportementsEleve;
import com.project.ebossy.service.ComportementsEleveService;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/comportements")
@CrossOrigin("*")
public class ComportementsController {

    private static final Logger logger = LoggerFactory.getLogger(ComportementsController.class);

    private final HttpSession httpSession;
    private final ComportementsService comportementsService;
    private final ClasseService classeService;
    private final ComportementsEleveService comportementsEleveService;
    private final EleveService eleveService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private LayoutService layoutService;

    @Autowired
    public ComportementsController(HttpSession httpSession, ComportementsService comportementsService, ClasseService classeService, ComportementsEleveService comportementsEleveService, EleveService eleveService) {
        this.httpSession = httpSession;
        this.comportementsService = comportementsService;
        this.classeService = classeService;
        this.comportementsEleveService = comportementsEleveService;
        this.eleveService = eleveService;
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
    public ModelAndView list(@RequestParam(name = "classe", required = false) Integer idClasse) {
        ModelAndView modelAndView = new ModelAndView("direction/layout");
        modelAndView.addObject("page", "direction/comportements/list");
        Ecole ecole = (Ecole) httpSession.getAttribute("ecole");
        List<Classe> classeList = classeService.findAll(ecole);
        List<Comportements> comportementsList = comportementsService.findAll();
        List<ComportementsEleve> eleveList = comportementsEleveService.findAll();    
        List<Eleve> eleveL = eleveService.findAllByEcole(ecole); 
        // Initialisez selectedComportements
        Comportements selectedComportements = comportementsList.isEmpty() ? null : comportementsList.get(0);
        
        modelAndView.addObject("classeList", classeList);
        modelAndView.addObject("comportementsList", comportementsList);
        modelAndView.addObject("eleveList", eleveList);
        modelAndView.addObject("eleveL", eleveL);
        modelAndView.addObject("selectedClasse", null);
        modelAndView.addObject("selectedComportements", selectedComportements);
        
        return modelAndView;
    }

    @PostMapping("/save")
    public String ComportementsSave(@RequestParam("nom") String nom) {
        Comportements comportements = new Comportements();
        comportements.setNom(nom);
        comportementsService.save(comportements);
        return "redirect:/comportements/form";
    }

    @PostMapping("/delete")
    public String professeurDelete(@RequestParam("idComportement") Integer id) {
        comportementsService.deleteById(id);
        return "redirect:/comportements/form";
    }
    @PostMapping("/update-eleve-comportement")
    @ResponseBody
    public void updateEleveComportement(@RequestParam("eleveId") int eleveId,
                                        @RequestParam("newComportement") int newComportementId) {
        comportementsEleveService.updateComportementsEleve(eleveId, newComportementId);
    }
}
