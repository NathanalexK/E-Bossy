package com.project.ebossy.controller;


import com.project.ebossy.repository.SexeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/prof")
public class ProfController {


    private final SexeRepository sexeRepository;

    public ProfController(SexeRepository sexeRepository) {
        this.sexeRepository = sexeRepository;
    }

    @GetMapping("/form")
    public ModelAndView form(Model model){
        ModelAndView modelAndView = new ModelAndView("direction/layout");
        modelAndView.addObject("page", "direction/prof/form");

        modelAndView.addObject("sexeList", sexeRepository.findAll());

        return modelAndView;
    }
}
