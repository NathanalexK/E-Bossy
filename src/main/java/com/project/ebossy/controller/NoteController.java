package com.project.ebossy.controller;


import com.project.ebossy.model.*;
import com.project.ebossy.service.*;
import com.project.ebossy.util.Calcul;
import com.project.ebossy.util.Role;
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
import java.util.Map;

@Controller
@RequestMapping("/note")
public class NoteController {

    private final LayoutService layoutService;
    private final MatiereProfService matiereProfService;
    private final HttpSession httpSession;
    private final EleveAnneeScolaireService eleveAnneeScolaireService;
    private final PeriodeNoteService periodeNoteService;
    private final RoleService roleService;
    private final NoteService noteService;

    public NoteController(LayoutService layoutService, MatiereProfService matiereProfService, HttpSession httpSession, EleveAnneeScolaireService eleveAnneeScolaireService, PeriodeNoteService periodeNoteService, RoleService roleService, NoteService noteService) {
        this.layoutService = layoutService;
        this.matiereProfService = matiereProfService;
        this.httpSession = httpSession;
        this.eleveAnneeScolaireService = eleveAnneeScolaireService;
        this.periodeNoteService = periodeNoteService;
        this.roleService = roleService;
        this.noteService = noteService;
    }

    @GetMapping("/form")
    public ModelAndView professorForm(
            Model model,
            @RequestParam(name = "matiereProf", required = false) MatiereProf matiereProf,
            @RequestParam(name = "periodeNote", required = false) PeriodeNote periodeNote
        ) {

        roleService.allowedRoles(Role.PROFESSEUR);
        ModelAndView modelAndView = layoutService.getLayout();
        modelAndView.addObject("page", "professeur/note/form");
        Professeur professeur = ((Professeur) httpSession.getAttribute("utilisateur"));

        List<MatiereProf> matiersProfs = matiereProfService.findAllByProfesseur(professeur, null);

        if(matiereProf == null) {
            matiereProf = matiersProfs.get(0);
        }
        if (matiereProf == null) {
            throw new RuntimeException("matiereProf is null");
        }

        System.out.println(matiereProf.getIdClasse().getIdNiveau().getNomNiveau());
        List<PeriodeNote> periodeNoteList = periodeNoteService.findAllByEcole(professeur.getIdEcole(), null);


        if(periodeNote == null) {
            periodeNote = periodeNoteList.get(0);
        }
        if(periodeNote == null) {
            throw new RuntimeException("periodeNote is null");
        }


        List<EleveAnneeScolaire> easList = eleveAnneeScolaireService.findAllByClasse(matiereProf.getIdClasse());
        Map<Eleve, Note> noteMap = noteService.findNoteByMatiereProf(matiereProf, periodeNote);
//
        modelAndView.addObject("matiereProfList", matiersProfs);
        modelAndView.addObject("periodeNoteList", periodeNoteList);
        modelAndView.addObject("noteMap", noteMap);
        modelAndView.addObject("easList", easList);
        modelAndView.addObject("selectedMatiereProf", matiereProf);
        modelAndView.addObject("selectedPeriodeNote", periodeNote);
        modelAndView.addObject("moyenne", Calcul.moyenneNote(noteMap));

        return modelAndView;
    }

    @GetMapping("bulletin")
    public ModelAndView bulletinEleve(
            @RequestParam("eleve") EleveAnneeScolaire eas
    ){
        ModelAndView modelAndView = layoutService.getLayout();
        modelAndView.addObject("page", "professeur/note/bulletin");

        Map<Matiere, Map<PeriodeNote, Note>> bulletinMap = noteService.getInversedBulletinDeNote(eas);
        List<PeriodeNote> periodeNoteList = periodeNoteService.findAllByEcole(eas.getIdEleve().getIdEcole(), eas.getIdAnneeScolaire());
        List<MatiereProf> matiereProfList = matiereProfService.findAllByClasse(eas.getIdClasse());

        modelAndView.addObject("bulletinMap", bulletinMap);
        modelAndView.addObject("periodeNoteList", periodeNoteList);
        modelAndView.addObject("matiereProfList", matiereProfList);
        return modelAndView;
    }

    @PostMapping("/save")
    public String onSave(
            @RequestParam("idNote[]") List<Integer> existedNoteList,
            @RequestParam("idMatiereProf") MatiereProf matiereProf,
            @RequestParam("idPeriode") PeriodeNote periodeNote,
            @RequestParam("idEleve[]") List<Eleve> eleveList,
            @RequestParam("note[]") List<Double> noteList,
            @RequestParam("appreciation[]") List<String> appreciationList,
            RedirectAttributes redirectAttributes
    ) {
        List<Note> notesToSave = new ArrayList<>();
        for(int i = 0; i < eleveList.size(); i++) {
            Note note = new Note();

            if(existedNoteList.get(i) != null) {
                note.setId(existedNoteList.get(i));
            }
            note.setMatiereProf(matiereProf);
//            note.setIdClasse(matiereProf.getIdClasse());
//            note.setIdMatiere(matiereProf.getIdMatiere());
            note.setIdPeriodeNote(periodeNote);
            note.setIdEleve(eleveList.get(i));
            note.setNote(noteList.get(i));
            note.setAppreciation(appreciationList.get(i));
            notesToSave.add(note);
        }
        noteService.saveAll(notesToSave);
        return "redirect:/note/form?matiereProf="+matiereProf.getId() + "&periodeNote=" + periodeNote.getId();
    }
}
