package com.project.ebossy.controller;


import com.project.ebossy.service.EcoleService;
import com.project.ebossy.service.MailService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {


    private final HttpSession httpSession;
    private final EcoleService ecoleService;
    private final MailService mailService;

    public HomeController(HttpSession httpSession, EcoleService ecoleService, MailService mailService) {
        this.httpSession = httpSession;
        this.ecoleService = ecoleService;
        this.mailService = mailService;
    }

    @RequestMapping("/")
    public String index(){
        httpSession.setAttribute("ecole", ecoleService.getEcoleById(2).get());
//        new Thread(() -> {
//            mailService.sendEmail("Kevin", "nathanalekskevin@gmail.com", "Hellos", "Hello it works");
//        }).start();
        return "redirect:/niveau/form";
    }
}
