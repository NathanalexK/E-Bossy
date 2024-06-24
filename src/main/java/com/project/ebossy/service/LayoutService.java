package com.project.ebossy.service;


import com.project.ebossy.model.Ecole;
import com.project.ebossy.util.Role;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Service
public class LayoutService {

    private final HttpSession httpSession;
    private final AnneeScolaireService anneeScolaireService;
    private final SessionService sessionService;

    public LayoutService(HttpSession httpSession, AnneeScolaireService anneeScolaireService, SessionService sessionService) {
        this.httpSession = httpSession;
        this.anneeScolaireService = anneeScolaireService;
        this.sessionService = sessionService;
    }

    public ModelAndView getLayout() {
        String role = httpSession.getAttribute("role").toString();

        ModelAndView mav =  switch (role) {
            case Role.ADMIN -> new ModelAndView("admin/layout");
            case Role.DIRECTEUR -> new ModelAndView("direction/layout");
            case Role.PROFESSEUR -> new ModelAndView("professeur/layout");
            case Role.SECRETAIRE -> new ModelAndView("secretaire/layout");
            case Role.TUTEUR -> new ModelAndView("tuteur/layout");
            case Role.ELEVE -> new ModelAndView("eleve/layout");
            default -> throw new RuntimeException("Undefined role: " + role);
        };

        mav.addObject("anneeScolaire", sessionService.getAnneeScolaire());
        mav.addObject("anneeScolaireList", anneeScolaireService.findAllByEcole(((Ecole) httpSession.getAttribute("ecole"))));
        return mav;
    }
}
