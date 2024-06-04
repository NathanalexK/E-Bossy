package com.project.ebossy.controller;


import com.project.ebossy.model.Ecole;
import com.project.ebossy.model.Niveau;
import com.project.ebossy.model.Note;
import com.project.ebossy.service.EcoleService;
import com.project.ebossy.service.NiveauService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

        httpSession.setAttribute("ecole", ecoleService.getEcoleById(2).get());

        modelAndView.addObject("page", "direction/niveau");
        return modelAndView;
    }

    @PostMapping("/save")
    public String niveauSave(
            @RequestParam("nom") String nom
    ) {
        Niveau niveau = new Niveau();
        Ecole idEcole = (Ecole) httpSession.getAttribute("ecole");
        niveau.setIdEcole(idEcole);
        niveau.setNomNiveau(nom);
        niveauService.save(niveau);
        return "redirect:/niveau/form";
    }

}
