package com.project.ebossy.controller;


import com.project.ebossy.model.Ecole;
import com.project.ebossy.model.Professeur;
import com.project.ebossy.service.EcoleService;
import com.project.ebossy.service.LayoutService;
import com.project.ebossy.service.MailService;
import com.project.ebossy.service.ProfesseurService;
import com.project.ebossy.util.Role;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {


    private final HttpSession httpSession;
    private final EcoleService ecoleService;
    private final MailService mailService;
    private final ProfesseurService professeurService;
    private final LayoutService layoutService;

    public HomeController(HttpSession httpSession, EcoleService ecoleService, MailService mailService, ProfesseurService professeurService, LayoutService layoutService) {
        this.httpSession = httpSession;
        this.ecoleService = ecoleService;
        this.mailService = mailService;
        this.professeurService = professeurService;
        this.layoutService = layoutService;
    }

    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("login/espace");
//        httpSession.setAttribute("role", "professeur");
//        Ecole myEcole = ecoleService.getEcoleById(2).get();
//        Professeur professeur = professeurService.findByIdentifiant("ranary@lcsmi.itaosy");
//        httpSession.setAttribute("role", Role.PROFESSEUR);
//        httpSession.setAttribute("professeur", professeur);
//        httpSession.setAttribute("anneeScolaire", myEcole.getAnneeScolaire());
//        httpSession.setAttribute("ecole", myEcole);
//        new Thread(() -> {
//            mailService.sendEmail("Kevin", "nathanalekskevin@gmail.com", "Hellos", "Hello it works");
//        }).start();
//        return "redirect:/niveau/form";
    }

    @RequestMapping("/erreur")
    public ModelAndView error(){
        ModelAndView modelAndView = layoutService.getLayout();
        modelAndView.addObject("page", "erreur/autorisation");

        return modelAndView;
    }
}
