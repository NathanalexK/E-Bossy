package com.project.ebossy.controller;


import com.project.ebossy.model.Ecole;
import com.project.ebossy.model.Niveau;
import com.project.ebossy.service.EcoleService;
import com.project.ebossy.service.NiveauService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/niveau")
public class NiveauController {


    private final HttpSession httpSession;
    private final NiveauService niveauService;
    private final EcoleService ecoleService;

    public NiveauController(HttpSession httpSession, NiveauService niveauService, EcoleService ecoleService) {
        this.httpSession = httpSession;
        this.niveauService = niveauService;
        this.ecoleService = ecoleService;
    }

    @GetMapping("/form")
    public ModelAndView niveauForm() {
        ModelAndView modelAndView = new ModelAndView("direction/layout");

        Ecole ecole = (Ecole) httpSession.getAttribute("ecole");

        modelAndView.addObject("niveauList", niveauService.findAll(ecole.getId()));

        modelAndView.addObject("page", "direction/niveau");
        return modelAndView;
    }

    @PostMapping("/save")
    public String niveauSave(
            @RequestParam("nom") String nom,
            @RequestParam("numero") Integer numero
    ) {
        Niveau niveau = new Niveau();
        Ecole idEcole = (Ecole) httpSession.getAttribute("ecole");
        niveau.setIdEcole(idEcole);
        niveau.setNomNiveau(nom);
        niveau.setNumero(numero);
        niveauService.save(niveau);
        return "redirect:/niveau/form";
    }

    @PostMapping("/delete")
    public String niveauDelete(@RequestParam("idNiveau") Integer id) {
        niveauService.deleteById(id);
        return "redirect:/niveau/form";
    }

//    @DeleteMapping("/del/{id}")
//    public String delete(@PathVariable Integer id) {
//        niveauService.deleteById(id);
//        return "redirect:/niveau/form";
//    }

}
