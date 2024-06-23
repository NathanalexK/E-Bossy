package com.project.ebossy.controller;


import com.project.ebossy.model.AnneeScolaire;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AnneeScolaireController {

    private final HttpSession httpSession;

    public AnneeScolaireController(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    @GetMapping("/anneeScolaire/set")
    public String setAnneeScolaire(@RequestParam("id") AnneeScolaire anneeScolaire, HttpServletRequest request) {
        httpSession.setAttribute("anneeScolaire", anneeScolaire);
        return "redirect:"+request.getHeader("Referer");
    }
}
