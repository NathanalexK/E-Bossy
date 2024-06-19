package com.project.ebossy.controller;


import com.project.ebossy.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

//    public String authenticate(
//            @RequestParam("identifiant") String identifiant,
//            @RequestParam("mdp") String mdp
//    ) {
//        String role = (String) httpSession.getAttribute("role");
//        Object user = authService.authenticate(identifiant, mdp, role);
//
//        if (user != null) {
//            httpSession.setAttribute("user", user);
////            httpSession.setAttribute("ecole", );
//        }
//    }
}
