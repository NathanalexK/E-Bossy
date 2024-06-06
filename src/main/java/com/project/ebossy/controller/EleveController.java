package com.project.ebossy.controller;

import com.project.ebossy.model.Ecole;
import com.project.ebossy.model.Eleve;
import com.project.ebossy.service.EcoleService;
import com.project.ebossy.service.EleveService;

import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/eleve")
public class EleveController {
    private final EleveService eleveService;
    private final HttpSession httpSession;
    private final EcoleService ecoleService;

    public EleveController(EcoleService ecoleService, HttpSession httpSession, EleveService eleveService) {
        this.eleveService = eleveService;
        this.httpSession = httpSession;
        this.ecoleService = ecoleService;
    }

    @GetMapping("/form")
    public ModelAndView form(){
        ModelAndView modelAndView = new ModelAndView("direction/layout");
        modelAndView.addObject("page", "direction/eleve/form");

        return modelAndView;
    }

    @PostMapping("/save")
    public String onSave(
    ){
        return "redirect:/eleve/form";
    }

    @PostMapping("/{id}")
    public String onUpdate(
    ){

        return "redirect:/eleve/form";
    }
}
