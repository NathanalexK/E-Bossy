package com.project.ebossy.controller;



import com.project.ebossy.model.Eleve;
import com.project.ebossy.model.PayeEcolage;
import com.project.ebossy.model.PeriodeEcolage;
import com.project.ebossy.repository.PayeEcolageRepository;
import com.project.ebossy.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.ebossy.model.Ecole;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/payeEcolage")
public class PayeEcolageController {

    private final ClasseService classeService;
    private final PayeEcolageService payeEcolageService;
    private final EleveService eleveService;
    private final PeriodeEcolageService periodeEcolageService;
    private final EleveNonPayerEcolageService eleveNonPayerEcolageService;
    private final HttpSession httpSession;
    private final PayeEcolageRepository payeEcolageRepository;

    public PayeEcolageController(EleveNonPayerEcolageService eleveNonPayerEcolageService,EleveService eleveService,PeriodeEcolageService periodeEcolageService, ClasseService classeService, HttpSession httpSession, PayeEcolageService payeEcolageService,
                                 PayeEcolageRepository payeEcolageRepository) {
        this.classeService = classeService;
        this.eleveNonPayerEcolageService = eleveNonPayerEcolageService;
        this.eleveService = eleveService;
        this.periodeEcolageService = periodeEcolageService;
        this.httpSession = httpSession;
        this.payeEcolageService = payeEcolageService;
        this.payeEcolageRepository = payeEcolageRepository;
    }
  

    @GetMapping("/form")
    public ModelAndView form(){
        ModelAndView modelAndView = new ModelAndView("direction/layout");
        modelAndView.addObject("page", "direction/payeEcolage/form");

        Ecole myEcole = ((Ecole) httpSession.getAttribute("ecole"));

        modelAndView.addObject("classeList", classeService.findAll(myEcole));

        return modelAndView;
    }

    @GetMapping("/list")
    public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView("direction/layout");
        modelAndView.addObject("page", "direction/payeEcolage/list");

        Ecole myEcole = ((Ecole) httpSession.getAttribute("ecole"));

        modelAndView.addObject("eleveList", eleveNonPayerEcolageService.getByEcole(myEcole.getId()));
        modelAndView.addObject("classeList", classeService.findAll(myEcole));

        return modelAndView;
    }

    @PostMapping("/list")
    public ModelAndView getEleve(
            @RequestParam("idclasse") int idClasse
    ){
        ModelAndView modelAndView = new ModelAndView("direction/layout");
        modelAndView.addObject("page", "direction/payeEcolage/list");

        Ecole myEcole = ((Ecole) httpSession.getAttribute("ecole"));

        modelAndView.addObject("classeList", classeService.findAll(myEcole));

        if(idClasse != 0){
            modelAndView.addObject("eleveList", eleveNonPayerEcolageService.getByClasse(myEcole.getId(),idClasse));

        }else{
            modelAndView.addObject("eleveList", eleveNonPayerEcolageService.getByEcole(myEcole.getId()));

        }


        return modelAndView;
    }

    @PostMapping("/save")
    public String onSave(
            @RequestParam("numero") int numero,
            @RequestParam("idclasse") int idClasse,
            @RequestParam("datePayement") String datePayement
    ){
         Ecole myEcole = ((Ecole) httpSession.getAttribute("ecole"));
         Eleve eleve = eleveService.getByNumero(numero, idClasse, myEcole.getId());
         System.out.println("le nom d'ecole  est :"+myEcole.getNomEcole());
         System.out.println("l'id d'ecole  est :"+myEcole.getId());
         System.out.println("le nom d'eleve est :"+eleve.getNom());
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
         LocalDate localDate = LocalDate.parse(datePayement, formatter);
         System.out.println("le date   est :"+localDate);
         PeriodeEcolage periodeEcolage = periodeEcolageService.getByDate(myEcole.getId(),localDate);
         System.out.println("l'anne du scolaire :"+periodeEcolage.getId());
         PayeEcolage payeEcolage = new PayeEcolage();
         payeEcolage.setIdEcole(myEcole);
         payeEcolage.setIdEleve(eleve);
         payeEcolage.setIdPeriodeEcolage(periodeEcolage);
         payeEcolage.setDatePayement(localDate);
        payeEcolageRepository.save(payeEcolage);
        return "redirect:/payeEcolage/form";
    }
}