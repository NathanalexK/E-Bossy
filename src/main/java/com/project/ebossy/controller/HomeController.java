package com.project.ebossy.controller;


import com.project.ebossy.service.EcoleService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {


    private final HttpSession httpSession;
    private final EcoleService ecoleService;

    public HomeController(HttpSession httpSession, EcoleService ecoleService) {
        this.httpSession = httpSession;
        this.ecoleService = ecoleService;
    }

    @RequestMapping("/")
    public String index(){
        httpSession.setAttribute("ecole", ecoleService.getEcoleById(2).get());
        return "redirect:/niveau/form";
    }
}
