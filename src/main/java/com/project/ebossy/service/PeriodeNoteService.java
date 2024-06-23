package com.project.ebossy.service;


import com.project.ebossy.model.AnneeScolaire;
import com.project.ebossy.model.Ecole;
import com.project.ebossy.model.PeriodeNote;
import com.project.ebossy.repository.NoteRepository;
import com.project.ebossy.repository.PeriodeNoteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeriodeNoteService {

    private final PeriodeNoteRepository periodeNoteRepository;
    private final NoteRepository noteRepository;

    public PeriodeNoteService(PeriodeNoteRepository periodeNoteRepository, NoteRepository noteRepository) {
        this.periodeNoteRepository = periodeNoteRepository;
        this.noteRepository = noteRepository;
    }


    public List<PeriodeNote> findAllByEcole(Ecole ecole) {
        return periodeNoteRepository.findByEcole(ecole);
    }

    public List<PeriodeNote> findAllByEcole(Ecole ecole, AnneeScolaire anneeScolaire) {
        if(anneeScolaire == null){
            anneeScolaire = ecole.getAnneeScolaire();
        }
        return periodeNoteRepository.findAllByEcoleWithAnneeScolaire(ecole, anneeScolaire);
    }


    public PeriodeNote save(PeriodeNote periodeNote) {
        return periodeNoteRepository.save(periodeNote);
    }

    public PeriodeNote findById(Integer id) {
        return periodeNoteRepository.findById(id).get();
    }

    public void deleteById(Integer id) {
        PeriodeNote periodeNote = periodeNoteRepository.findById(id).orElse(null);
        if(periodeNote != null) {
            noteRepository.deleteByIdPeriodeNote(periodeNote);
        }
        periodeNoteRepository.deleteById(id);
    }

    @Transactional
    public void delete(PeriodeNote periodeNote) {
        noteRepository.deleteByIdPeriodeNote(periodeNote);
        periodeNoteRepository.delete(periodeNote);
    }
}
