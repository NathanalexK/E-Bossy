package com.project.ebossy.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/calendrier")
public class CalendrierScolaireController {

    @GetMapping("/form")
    public ModelAndView calendrierForm() {
        ModelAndView modelAndView = new ModelAndView("direction/layout");
        modelAndView.addObject("page", "direction/calendrier/form");

        return modelAndView;
    }
}
