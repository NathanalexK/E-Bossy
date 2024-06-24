package com.project.ebossy.controller;


import com.project.ebossy.exception.NotFoundException;
import com.project.ebossy.model.*;
import com.project.ebossy.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/matiereProf")
public class MatiereProfController {

    private final ClasseService classeService;
    private final HttpSession httpSession;
    private final ProfesseurService professeurService;
    private final MatiereService matiereService;
    private final MatiereProfService matiereProfService;
    private final LayoutService layoutService;

    public MatiereProfController(ClasseService classeService, HttpSession httpSession, ProfesseurService professeurService, MatiereService matiereService, MatiereProfService matiereProfService, LayoutService layoutService) {
        this.classeService = classeService;
        this.httpSession = httpSession;
        this.professeurService = professeurService;
        this.matiereService = matiereService;
        this.matiereProfService = matiereProfService;
        this.layoutService = layoutService;
    }

    @GetMapping("/form")
    public ModelAndView form(Model model, @RequestParam(value = "classe", required = false) Integer idClasse) {
        ModelAndView modelAndView = layoutService.getLayout();
        modelAndView.addObject("page", "direction/matiereProf/form");

        Ecole myEcole = ((Ecole) httpSession.getAttribute("ecole"));
        List<Classe> classes = classeService.findAll(myEcole);

        Classe myClasse = null;//        System.out.println(c);

        if(classes.isEmpty()){
            throw new NotFoundException("Aucune classe trouvée!<br>Veuillez d'abord creer une classe");
        }

        if(idClasse != null) myClasse = classeService.findById(idClasse);
        if(myClasse == null) myClasse = classes.get(0);
        Niveau myNiveau =  myClasse.getIdNiveau();

        model.addAttribute("classeList", classes);
        modelAndView.addObject("profList", professeurService.findAllByEcole(myEcole));
        modelAndView.addObject("matiereProf", matiereProfService.findMatiereProfByClasse(myClasse));
        modelAndView.addObject("matiereProfList", matiereProfService.findAllByClasse(myClasse));
        modelAndView.addObject("classe", myClasse);

        return modelAndView;
    }

    @PostMapping("/save")
    public String onSave(
            @RequestParam("idClasse") Classe classe,
            @RequestParam("idMatiere[]") List<Matiere> matiereList,
            @RequestParam("idProfs[]") List<Professeur> professeurList,
            RedirectAttributes redirectAttributes
    ){
        Ecole myEcole = ((Ecole) httpSession.getAttribute("ecole"));
        List<MatiereProf> matiereProfList = new ArrayList<>();
        for (int i = 0; i < professeurList.size(); i++) {
            MatiereProf matiereProf = matiereProfService.findMatiereProf(classe, matiereList.get(i));

            if(matiereProf == null){
                matiereProf = new MatiereProf();
            }

            matiereProf.setIdClasse(classe);
            matiereProf.setIdMatiere(matiereList.get(i));
            matiereProf.setIdProf(professeurList.get(i));
            matiereProf.setIdAnneeScolaire(myEcole.getAnneeScolaire());

            matiereProfList.add(matiereProf);
        }

        for (MatiereProf matiereProf : matiereProfList) {
            matiereProfService.save(matiereProf);
        }

        return "redirect:/matiereProf/form?classe=" + classe.getId();
    }


}
