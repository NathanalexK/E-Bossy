package com.project.ebossy.controller;

import com.project.ebossy.model.Ecole;
import com.project.ebossy.service.NiveauService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/matiere")
public class MatiereController {


    private final NiveauService niveauService;
    private final HttpSession httpSession;

    public MatiereController(NiveauService niveauService, HttpSession httpSession) {
        this.niveauService = niveauService;
        this.httpSession = httpSession;
    }

    @GetMapping("/form")
    public ModelAndView form(){
        ModelAndView modelAndView = new ModelAndView("direction/layout");
        modelAndView.addObject("page", "direction/matiere/form");

        Ecole myEcole = ((Ecole) httpSession.getAttribute("ecole"));

        modelAndView.addObject("niveauList", niveauService.findAll(myEcole.getIdTypeEcole()));

        return modelAndView;
    }
}
