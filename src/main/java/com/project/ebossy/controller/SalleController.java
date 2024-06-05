package com.project.ebossy.controller;


import com.project.ebossy.model.Ecole;
import com.project.ebossy.model.Salle;
import com.project.ebossy.service.SalleService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/salle")
public class SalleController {


    private final SalleService salleService;
    private final HttpSession httpSession;

    public SalleController(SalleService salleService, HttpSession httpSession) {
        this.salleService = salleService;
        this.httpSession = httpSession;
    }

    @GetMapping("/form")
    public ModelAndView salleForm() {
        ModelAndView modelAndView = new ModelAndView("direction/layout");
        modelAndView.addObject("page", "direction/salle/form");
        modelAndView.addObject("salleList", salleService.findAllByEcole(((Ecole) httpSession.getAttribute("ecole"))));
//        modelAndView.addObject("salleList", salleService.getSalleDisponibles(((Ecole) httpSession.getAttribute("ecole"))));
        return modelAndView;
    }

    @PostMapping("/save")
    public String onSave(
            @RequestParam("numero") String numero,
            @RequestParam("capacite") int capacite
    ) {
        Salle salle = new Salle();
        salle.setNumero(numero);
        salle.setCapacite(capacite);
        salle.setIdEcole(((Ecole) httpSession.getAttribute("ecole")));
        salleService.save(salle);
        return "redirect:/salle/form";
    }

    @PostMapping("/delete")
    public String onDelete(@RequestParam("idSalle") int idSalle) {
        salleService.delete(idSalle);
        return "redirect:/salle/form";

    }
}
