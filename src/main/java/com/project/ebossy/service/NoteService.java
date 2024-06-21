package com.project.ebossy.service;


import com.project.ebossy.model.*;
import com.project.ebossy.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoteService {
    private final NoteRepository noteRepository;
    private final EleveService eleveService;
    private final EleveAnneeScolaireService eleveAnneeScolaireService;

    public NoteService(NoteRepository noteRepository, EleveService eleveService, EleveAnneeScolaireService eleveAnneeScolaireService) {
        this.noteRepository = noteRepository;
        this.eleveService = eleveService;
        this.eleveAnneeScolaireService = eleveAnneeScolaireService;
    }

    public void save(Note note) {
    }


    public List<Note> saveAll(List<Note> notes) {
        return noteRepository.saveAll(notes);
    }



    public List<Note> findAllByMatiereProf(MatiereProf matiereProf) {
        return noteRepository.findAllByMatiereProf(matiereProf);
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
}
