package com.project.ebossy.controller;


import com.project.ebossy.exception.AuthException;
import com.project.ebossy.model.AnneeScolaire;
import com.project.ebossy.model.Ecole;
import com.project.ebossy.service.AuthService;
import com.project.ebossy.service.SessionService;
import com.project.ebossy.util.Role;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthController {

    private final HttpSession httpSession;
    private final AuthService authService;
    private final SessionService sessionService;

    public AuthController(HttpSession httpSession, AuthService authService, SessionService sessionService) {
        this.httpSession = httpSession;
        this.authService = authService;
        this.sessionService = sessionService;
    }

    @GetMapping("/login/form")
    public ModelAndView login(
            @RequestParam("role") String role
    ) {
        httpSession.setAttribute("role", role);
        ModelAndView modelAndView = new ModelAndView("login/connexion");
        modelAndView.addObject("role", role);
        return modelAndView;
    }

    @PostMapping("/login/authenticate")
    public String authenticate(
            @RequestParam("identifiant") String identifiant,
            @RequestParam("mdp") String mdp
    ) {
        String role = (String) httpSession.getAttribute("role");
        Object user = authService.authenticate(identifiant, mdp, role);
        System.out.println(role);

        if(user == null) throw  new AuthException("Identifiant ou mot de passe invalide");

        Ecole ecole = sessionService.getEcole();
        if(ecole == null) return "redirect:/login/form";

        AnneeScolaire anneeScolaire = sessionService.getAnneeScolaire();
        if(anneeScolaire == null) {
            if(role.equals(Role.DIRECTEUR)) {
                return "redirect:/anneeScolaire/form";
            }
            throw new AuthException("Annee scolaire non trouvée");
        }

        httpSession.setAttribute("utilisateur", user);
        httpSession.setAttribute("anneeScolaire", ((Ecole) httpSession.getAttribute("ecole")).getAnneeScolaire());
        System.out.println(((Ecole) httpSession.getAttribute("ecole")).getNomEcole());
        return "redirect:/niveau/form";

    }

    @GetMapping("/logout")
    public String logout() {
        httpSession.invalidate();
        return "redirect:/";
    }
}
