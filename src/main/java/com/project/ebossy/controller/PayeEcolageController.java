package com.project.ebossy.controller;



import com.project.ebossy.model.Eleve;
import com.project.ebossy.model.EleveAnneeScolaire;
import com.project.ebossy.model.PayeEcolage;
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
    private final EleveAnneeScolaireService eleveAnneeScolaireService;
    private final PeriodeEcolageService periodeEcolageService;
    private final EleveNonPayerEcolageService eleveNonPayerEcolageService;
    private final HttpSession httpSession;
    private final PayeEcolageRepository payeEcolageRepository;

    public PayeEcolageController(EleveNonPayerEcolageService eleveNonPayerEcolageService,EleveAnneeScolaireService eleveAnneeScolaireService,PeriodeEcolageService periodeEcolageService, ClasseService classeService, HttpSession httpSession, PayeEcolageService payeEcolageService,
                                 PayeEcolageRepository payeEcolageRepository) {
        this.classeService = classeService;
        this.eleveNonPayerEcolageService = eleveNonPayerEcolageService;
        this.eleveAnneeScolaireService = eleveAnneeScolaireService;
        this.periodeEcolageService = periodeEcolageService;
        this.httpSession = httpSession;
        this.payeEcolageService = payeEcolageService;
        this.payeEcolageRepository = payeEcolageRepository;
    }
  

    @GetMapping("/form")
    public ModelAndView form(@RequestParam("eleve") EleveAnneeScolaire eas){
        ModelAndView modelAndView = new ModelAndView("direction/layout");
        modelAndView.addObject("page", "direction/payeEcolage/form");

        Ecole myEcole = ((Ecole) httpSession.getAttribute("ecole"));
        System.out.println(eas.getId());
        modelAndView.addObject("periodeEcolages", periodeEcolageService.getByNonPayerEleve(eas.getId()));
        System.out.println(periodeEcolageService.getByNonPayerEleve(eas.getIdEleve().getId()).size());
        modelAndView.addObject("eleveId", eas);

        return modelAndView;
    }



    @PostMapping("/save")
    public String onSave(
            @RequestParam("idperiode") int idPeriode,
            @RequestParam("eleveId") int idEleve,
            @RequestParam("datePayement") String datePayement
    ){
        PayeEcolage payeEcolage = new PayeEcolage();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(datePayement, formatter);
        payeEcolage.setDatePayement(localDate);
        payeEcolage.setIdPeriodeEcolage(periodeEcolageService.getById(idPeriode).get());
        payeEcolage.setIdEleveAnneeScolaire(eleveAnneeScolaireService.findById(idEleve));
        payeEcolageRepository.save(payeEcolage);
        return "redirect:/eleve/list";
    }
}