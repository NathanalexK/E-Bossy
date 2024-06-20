package com.project.ebossy.service;


import com.project.ebossy.model.*;
import com.project.ebossy.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NoteService {
    private final NoteRepository noteRepository;
    private final EleveService eleveService;
    private final EleveAnneeScolaireService eleveAnneeScolaireService;
    private final PeriodeNoteService periodeNoteService;
    private final MatiereProfService matiereProfService;

    public NoteService(NoteRepository noteRepository, EleveService eleveService, EleveAnneeScolaireService eleveAnneeScolaireService, PeriodeNoteService periodeNoteService, MatiereProfService matiereProfService) {
        this.noteRepository = noteRepository;
        this.eleveService = eleveService;
        this.eleveAnneeScolaireService = eleveAnneeScolaireService;
        this.periodeNoteService = periodeNoteService;
        this.matiereProfService = matiereProfService;
    }

    public void save(Note note) {
    }


    public List<Note> saveAll(List<Note> notes) {
        return noteRepository.saveAll(notes);
    }



    public List<Note> findAllByMatiereProf(MatiereProf matiereProf) {
        return noteRepository.findAllByMatiereProf(matiereProf);
    }

    public List<Note> findAllByEleveAnneeScolaire(EleveAnneeScolaire eleveAnneeScolaire) {
        return noteRepository.findByIdEleveAndIdPeriodeNote_IdAnneeScolaire(eleveAnneeScolaire.getIdEleve(), eleveAnneeScolaire.getIdAnneeScolaire());
    }



    public Map<Eleve, Note> findNoteByMatiereProf(MatiereProf matiereProf, PeriodeNote periodeNote) {
        Map<Eleve, Note> noteMap = new HashMap<>();
        List<EleveAnneeScolaire> eleveList = eleveAnneeScolaireService.findAllByClasse(matiereProf.getIdClasse());
        List<Note> noteList = noteRepository.findAllByMatiereProfWithPeriodeNote(matiereProf, periodeNote);

        System.out.println("mp: " + matiereProf.getId());
        System.out.println("pn: " + periodeNote.getId());
        for (Note note : noteList) {
            System.out.println(note.getIdEleve().getNom() + " " + note.getNote());
        }


        for (EleveAnneeScolaire eleve : eleveList) {
            noteMap.put(eleve.getIdEleve(), null);
        }

        for (Note note : noteList) {
            System.out.println(note.getNote());
            noteMap.put(note.getIdEleve(), note);
        }

        System.out.println(noteMap);
        return noteMap;
    }


    public Map<Matiere, Note> getBulletinDeNote(EleveAnneeScolaire eas , PeriodeNote periodeNote){
        List<MatiereProf> matiereList = matiereProfService.findAllByClasse(eas.getIdClasse());
        List<Note> noteList = findAllByEleveAnneeScolaire(eas);

        Map<Matiere, Note> noteMap = new LinkedHashMap<>();

        for(MatiereProf mp : matiereList) {
            noteMap.put(mp.getIdMatiere(), null);
        }

        for(Note note : noteList) {
            noteMap.put(note.getMatiereProf().getIdMatiere(), note);
        }

        return noteMap;
    }


    public Map<PeriodeNote, Map<Matiere, Note>> getBulletinDeNote(EleveAnneeScolaire eas) {
        List<PeriodeNote> periodeNoteList = periodeNoteService.findAllByEcole(eas.getIdAnneeScolaire().getIdEcole(), eas.getIdAnneeScolaire());
        List<MatiereProf> matiereProfList = matiereProfService.findAllByClasse(eas.getIdClasse());
        List<Note> noteList = findAllByEleveAnneeScolaire(eas);

        Map<PeriodeNote, Map<Matiere, Note>> bulletinMap = new LinkedHashMap<>();

        for (PeriodeNote periodeNote : periodeNoteList) {
            Map<Matiere, Note> noteMap = new LinkedHashMap<>();

            for (MatiereProf matiereProf : matiereProfList) {
                noteMap.put(matiereProf.getIdMatiere(), null);
            }

            bulletinMap.put(periodeNote, noteMap);
        }

        for(Note note : noteList) {

        }

    }
}
