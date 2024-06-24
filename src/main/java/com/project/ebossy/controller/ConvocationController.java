package com.project.ebossy.controller;

import com.project.ebossy.model.*;
import com.project.ebossy.service.ClasseService;
import com.project.ebossy.service.ConvocationService;
import com.project.ebossy.service.EleveAnneeScolaireService;
import com.project.ebossy.service.EleveService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/convocation")
public class ConvocationController {

    private final HttpSession httpSession;
    private final ConvocationService convocationService;
    private final ClasseService classeService;
    private final EleveService eleveService;
    private final EleveAnneeScolaireService eleveAnneeScolaireService;

    public ConvocationController(EleveAnneeScolaireService eleveAnneeScolaireService,EleveService eleveService, HttpSession httpSession, ConvocationService convocationService, ClasseService classeService) {
        this.httpSession = httpSession;
        this.eleveAnneeScolaireService = eleveAnneeScolaireService;
        this.eleveService = eleveService;
        this.convocationService = convocationService;
        this.classeService = classeService;
    }

    @GetMapping("/form")
    public ModelAndView form(){
        ModelAndView modelAndView = new ModelAndView("direction/layout");
        modelAndView.addObject("page", "direction/convocation/form");

        Ecole myEcole = (Ecole) httpSession.getAttribute("ecole");
        modelAndView.addObject("classeList", classeService.findAll(myEcole));

        return modelAndView;
    }

    @GetMapping("/eleves")
    @ResponseBody
    public ResponseEntity<?> getElevesByClasse(@RequestParam int classeId) {
        Classe classe = classeService.findById(classeId);
        List<EleveAnneeScolaire> eleves = eleveService.findAllByClasse(classe);

        List<Eleve> eleves1 = new ArrayList<>();
        for (int i = 0; i < eleves.size(); i++) {
            Eleve eleve = eleveService.getByIdEleve(eleves.get(i).getIdEleve().getId()).get();
            Eleve teste = new Eleve();
            teste.setNom(eleve.getNom());
            teste.setPrenom(eleve.getPrenom());
            teste.setId(eleves.get(i).getId());
            eleves1.add(teste);
            System.out.println(eleve.getNom());
        }
        System.out.println(eleves.size());
        System.out.println(eleves1.size());
        return ResponseEntity.ok(eleves1);
    }

    @PostMapping("/save")
    public String saveConvocation(
            @RequestParam("idclasse") String idclasse,
            @RequestParam("dateConvocation") String dateConvocation,
            @RequestParam("motif") String motif,
            @RequestParam("selectedEleves") String selectedEleves) {

        String[] eleveIds = selectedEleves.split(",");

        System.out.println("Classe ID: " + idclasse);
        System.out.println("Date de Convocation: " + dateConvocation);
        System.out.println("Motif: " + motif);
        System.out.println("Élèves sélectionnés: " + Arrays.toString(eleveIds));

        Ecole myEcole = (Ecole) httpSession.getAttribute("ecole");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateConvocation, formatter);
        for (int i = 0; i < eleveIds.length; i++) {
            Convocation convocation = new Convocation();
            convocation.setIdEcole(myEcole);
            convocation.setIdEleveAnneeScolaire(eleveAnneeScolaireService.findById(Integer.parseInt(eleveIds[i]) ));
            convocation.setDateConvocation(localDate);
            convocation.setMotif(motif);
            convocationService.save(convocation);
        }

        return "redirect:/convocation/form";
    }
}
