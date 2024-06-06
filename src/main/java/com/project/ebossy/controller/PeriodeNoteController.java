package com.project.ebossy.controller;


import com.project.ebossy.model.PeriodeNote;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/periode-note")
public class PeriodeNoteController {

    public ModelAndView periodeNoteForm() {
        ModelAndView modelAndView = new ModelAndView("direction/layout");
        modelAndView.addObject("page", "direction/periodeExamen/form");
//        modelAndView.addObject("periodeNote", new Perio);

        return modelAndView;
    }
}
