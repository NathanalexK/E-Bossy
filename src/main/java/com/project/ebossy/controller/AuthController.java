package com.project.ebossy.controller;


import com.project.ebossy.model.Ecole;
import com.project.ebossy.service.AuthService;
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

    public AuthController(HttpSession httpSession, AuthService authService) {
        this.httpSession = httpSession;
        this.authService = authService;
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

        if (user != null) {
            httpSession.setAttribute("utilisateur", user);
            System.out.println(((Ecole) httpSession.getAttribute("ecole")).getNomEcole());
            return "redirect:/niveau/form";
        }
        return "redirect:/login/form";
    }

    @GetMapping("/logout")
    public String logout() {
        httpSession.invalidate();
        return "redirect:/";
    }
}
