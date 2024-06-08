package com.project.ebossy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.ebossy.model.Ecole;
import com.project.ebossy.service.ClasseService;
import com.project.ebossy.service.EleveService;
import com.project.ebossy.service.PayeEcolageService;
import com.project.ebossy.service.PeriodeEcolageService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/payeEcolage")
public class PayeEcolageController {

    private final ClasseService classeService;
    private final PayeEcolageService payeEcolageService;
    private final HttpSession httpSession;

    public PayeEcolageController( ClasseService classeService, HttpSession httpSession, PayeEcolageService payeEcolageService) {
        this.classeService = classeService;
        this.httpSession = httpSession;
        this.payeEcolageService = payeEcolageService;
    }
  

    @GetMapping("/form")
    public ModelAndView form(){
        ModelAndView modelAndView = new ModelAndView("direction/layout");
        modelAndView.addObject("page", "direction/payeEcolage/form");

        Ecole myEcole = ((Ecole) httpSession.getAttribute("ecole"));

        modelAndView.addObject("classeList", classeService.findAll(myEcole));

        return modelAndView;
    }

    @PostMapping("/save")
    public void onSave(
            @RequestParam("numero") int numero,
            @RequestParam("idclasse") int idClasse,
            @RequestParam("datePayement") String datePayement
    ){
        Ecole myEcole = ((Ecole) httpSession.getAttribute("ecole"));
        // Eleve eleve =eleveService.getByNumeroAndClasseAndEcole(numero, idClasse, myEcole.getId());
        System.out.println(myEcole.getId());
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // LocalDate localDate = LocalDate.parse(datePayement, formatter);
        // PeriodeEcolage periodeEcolage = periodeEcolageService.getByDate(localDate);
        // PayeEcolage payeEcolage = new PayeEcolage();
        // payeEcolage.setIdEcole(myEcole);
        // payeEcolage.setIdEleve(eleve);
        // payeEcolage.setDatePayement(localDate);
        // payeEcolage.setIdPeriodeEcolage(periodeEcolage);
        // payeEcolageService.save(payeEcolage);
    }
}