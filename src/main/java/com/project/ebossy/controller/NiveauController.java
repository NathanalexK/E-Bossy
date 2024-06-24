package com.project.ebossy.controller;


import com.project.ebossy.model.AnneeScolaire;
import com.project.ebossy.model.Ecole;
import com.project.ebossy.model.Niveau;
import com.project.ebossy.service.*;
import com.project.ebossy.util.Role;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/niveau")
@CrossOrigin("*")
public class NiveauController {


    @Autowired
    private HttpSession httpSession;

    @Autowired
    private NiveauService niveauService;

    @Autowired
    private EcoleService ecoleService;

    @Autowired
    private LayoutService layoutService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private SessionService sessionService;

//    public NiveauController(HttpSession httpSession, NiveauService niveauService, EcoleService ecoleService) {
//        this.httpSession = httpSession;
//        this.niveauService = niveauService;
//        this.ecoleService = ecoleService;
//    }

    @GetMapping("/form")
    public ModelAndView niveauForm() {
        roleService.allowedRoles(
            Role.DIRECTEUR
        );

        ModelAndView modelAndView = layoutService.getLayout();

        Ecole ecole = (Ecole) httpSession.getAttribute("ecole");
        AnneeScolaire anneeScolaire = ((AnneeScolaire) httpSession.getAttribute("anneeScolaire"));
//        Ecole ecole = null;
        modelAndView.addObject("niveauList", niveauService.findAllByAnneeScolaire(anneeScolaire));

        modelAndView.addObject("page", "direction/niveau");
        return modelAndView;
    }

    @PostMapping("/save")
    public String niveauSave(
            @RequestParam("nom") String nom,
            @RequestParam("numero") Integer numero
    ) {
        Niveau niveau = new Niveau();
        Ecole idEcole = (Ecole) httpSession.getAttribute("ecole");
        niveau.setIdEcole(idEcole);
        niveau.setNomNiveau(nom);
        niveau.setIdAnneeScolaire(sessionService.getAnneeScolaire());
        niveau.setNumero(numero);
        niveauService.save(niveau);
        return "redirect:/niveau/form";
    }

    @PostMapping("/delete")
    public String niveauDelete(@RequestParam("idNiveau") Integer id) {
        niveauService.deleteById(id);
        return "redirect:/niveau/form";
    }
}
