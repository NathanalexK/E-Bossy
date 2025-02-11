package com.project.ebossy.controller;

import com.project.ebossy.model.*;
import com.project.ebossy.repository.SexeRepository;
import com.project.ebossy.service.*;

import com.project.ebossy.exception.NotFoundException;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/eleve")
public class EleveController {
    private final EleveService eleveService;
    private final HttpSession httpSession;
    private final EcoleService ecoleService;
    private final SexeRepository sexeRepository;
    private final NiveauService niveauService;
    private final TuteurService tuteurService;
    private final EleveAnneeScolaireService eleveAnneeScolaireService;
    private final ClasseService classeService;

    private static String UPLOAD_DIR = "image/eleve/";
    private final FileService fileService;
    private final LayoutService layoutService;
    private final SessionService sessionService;

    public EleveController(EcoleService ecoleService, HttpSession httpSession, EleveService eleveService, SexeRepository sexeRepository, NiveauService niveauService, TuteurService tuteurService, EleveAnneeScolaireService eleveAnneeScolaireService, ClasseService classeService, FileService fileService, LayoutService layoutService, SessionService sessionService) {
        this.eleveService = eleveService;
        this.httpSession = httpSession;
        this.ecoleService = ecoleService;
        this.sexeRepository = sexeRepository;
        this.niveauService = niveauService;
        this.tuteurService = tuteurService;
        this.eleveAnneeScolaireService = eleveAnneeScolaireService;
        this.classeService = classeService;
        this.fileService = fileService;
        this.layoutService = layoutService;
        this.sessionService = sessionService;
    }

    @GetMapping("/form")
    public ModelAndView form(Model model){
        ModelAndView modelAndView = layoutService.getLayout();
        modelAndView.addObject("page", "direction/eleve/form");

        Ecole myEcole = ((Ecole) httpSession.getAttribute("ecole"));

        modelAndView.addObject("sexeList", sexeRepository.findAll());
        modelAndView.addObject("niveauList", niveauService.findAll(myEcole.getId()));
        modelAndView.addObject("erreur", model.getAttribute("erreur"));
        modelAndView.addObject("success", model.getAttribute("success"));

        return modelAndView;
    }

    @GetMapping("/list")
    public ModelAndView list(
            @RequestParam(name = "classe", required = false) Integer idClasse,
            @RequestParam(name = "page", defaultValue = "0") Integer page
    ){
        ModelAndView modelAndView = layoutService.getLayout();
        modelAndView.addObject("page", "direction/eleve/list");

        Ecole myEcole = ((Ecole) httpSession.getAttribute("ecole"));
        AnneeScolaire anneeScolaire = ((AnneeScolaire) httpSession.getAttribute("anneeScolaire"));
        List<Classe> classeList = classeService.findAllByAnneeScolaire(anneeScolaire);

        if(classeList.isEmpty()){
            throw new NotFoundException("Aucune classe trouvé pour cette annee scolaire <br> " + anneeScolaire.getNom() + "<br> Creer d'abord une classe pour continuer");
        }

        Classe myClasse = null;
        if(idClasse != null){
            myClasse = classeService.findById(idClasse);
        }
        if(myClasse == null){
            myClasse = classeList.get(0);
        }

        Page<EleveAnneeScolaire> eleves = eleveAnneeScolaireService.findAllByClasse(myClasse, page);
        List<EleveAnneeScolaire> pasDeClasseList = eleveService.findAllElevePasDeClasse(myClasse.getIdNiveau());


        modelAndView.addObject("classe", myClasse);
        modelAndView.addObject("eleveList", eleves);
        modelAndView.addObject("classeList", classeList);modelAndView.addObject("sexeList", sexeRepository.findAll());
        modelAndView.addObject("pasDeClasseList", pasDeClasseList);
        modelAndView.addObject("totalPages", eleves.getTotalPages());
        modelAndView.addObject("currentPage", page);

        return modelAndView;
    }

    @GetMapping("/critere-list")
    public ModelAndView list(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "nom", required = false) String nom,
            @RequestParam(name = "prenom", required = false) String prenom,
            @RequestParam(name = "idSexe", required = false) Sexe idSexe,
            @RequestParam(name = "dateDebut", required = false) LocalDate dateDebut,
            @RequestParam(name = "dateFin", required = false) LocalDate dateFin
    ){
        ModelAndView modelAndView = layoutService.getLayout();
        modelAndView.addObject("page", "direction/eleve/critere");

        Ecole myEcole = ((Ecole) httpSession.getAttribute("ecole"));
        AnneeScolaire myAnneeScolaire = ((AnneeScolaire) httpSession.getAttribute("anneeScolaire"));

        Page<EleveAnneeScolaire> eleveAnneeScolairePage = eleveAnneeScolaireService.searchEleveAnneeScolaire(myAnneeScolaire, nom, prenom, idSexe, dateDebut, dateFin, page);
        modelAndView.addObject("eleveList", eleveAnneeScolairePage.getContent());
        modelAndView.addObject("totalPages", eleveAnneeScolairePage.getTotalPages());
        modelAndView.addObject("nom", nom);
        modelAndView.addObject("prenom", prenom);
        modelAndView.addObject("idSexe", idSexe);
        modelAndView.addObject("dateDebut", dateDebut);
        modelAndView.addObject("dateFin", dateFin);
        modelAndView.addObject("currentPage", page);

        return modelAndView;
    }

    @PostMapping("/save")
    @Transactional
    public String onSave(
            @RequestParam(name = "id", required = false) Integer id,
            @RequestParam("nom") String nom,
            @RequestParam("prenom") String prenom,
            @RequestParam("idSexe") Sexe idSexe,
            @RequestParam("date") LocalDate date,
            @RequestParam("email") String email,
            @RequestParam("mdp") String mdp,
            @RequestParam("idNiveau") Niveau niveau,
            @RequestParam("identifiant") String identifiant,
            @RequestParam(name = "photo", required = false) MultipartFile photo,
            RedirectAttributes redirectAttributes
    ){
        Map<String, String> errors = new HashMap<>();
        Tuteur tuteur = tuteurService.findByEmail(email);

        String photoPath = null;
        try {
            photoPath = fileService.saveFile(photo, UPLOAD_DIR, "0.jpg");
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/niveau/form";
        }

        if(tuteur == null){
            redirectAttributes.addAttribute("erreur", "Aucun tutueur correspond à: " + email);
        }
        Ecole myEcole = ((Ecole) httpSession.getAttribute("ecole"));
        Eleve eleve = new Eleve();
        eleve.setNom(nom);
        eleve.setPrenom(prenom);
        eleve.setIdSexe(idSexe);
        eleve.setDateNaissance(date);
        eleve.setIdTuteur(tuteur);
        eleve.setIdentifiant(identifiant);
        eleve.setMdp(mdp);
        eleve.setIdEcole(myEcole);
        eleve.setPhoto(photoPath);
        Eleve saved = eleveService.save(eleve);

        EleveAnneeScolaire eas = new EleveAnneeScolaire();
        eas.setIdEleve(saved);
        eas.setIdAnneeScolaire(myEcole.getAnneeScolaire());
        eas.setIdNiveau(niveau);
        eleveAnneeScolaireService.save(eas);

        redirectAttributes.addAttribute("success", nom + " " + prenom + "a été bien inscris");
        return "redirect:/eleve/form";
    }

    @PostMapping("/affecter")
    @Transactional
    public String onAffectation(
            @RequestParam("idClasse") Classe classe,
            @RequestParam("idEleve[]") List<EleveAnneeScolaire> eleveList
    ){
        for(EleveAnneeScolaire eleve : eleveList){
            eleve.setIdClasse(classe);
            eleveAnneeScolaireService.save(eleve);
        }

        return "redirect:/eleve/list?classe=" + classe.getId();
    }

    @GetMapping("/information")
    public ModelAndView information(@RequestParam("id") Integer id){
        ModelAndView modelAndView = layoutService.getLayout();
        modelAndView.addObject("page", "direction/eleve/information");

        Eleve myEleve = eleveService.findEleveById(id);
        List<EleveAnneeScolaire> eleveAnneeScolaire =
                myEleve.getEleveAnneeScolaire()
                        .stream()
                                .sorted(Comparator.comparingInt(EleveAnneeScolaire::getId))
                                        .toList();

        modelAndView.addObject("eleve", myEleve);
        modelAndView.addObject("parcours", eleveAnneeScolaire);

        Niveau myNiveau = eleveAnneeScolaire.get(0).getIdNiveau();

        modelAndView.addObject("niveau", myNiveau);
        modelAndView.addObject("classe", eleveAnneeScolaire.get(0).getIdClasse());

        return modelAndView;
    }

    @PostMapping("/{id}")
    public String onUpdate(
    ){

        return "redirect:/eleve/form";
    }
}
