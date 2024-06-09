package com.project.ebossy.service;


import com.project.ebossy.model.Ecole;
import com.project.ebossy.model.PeriodeNote;
import com.project.ebossy.repository.PeriodeNoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeriodeNoteService {

    private final PeriodeNoteRepository periodeNoteRepository;

    public PeriodeNoteService(PeriodeNoteRepository periodeNoteRepository) {
        this.periodeNoteRepository = periodeNoteRepository;
    }

    public List<PeriodeNote> findAllByEcole(Ecole ecole) {
        return periodeNoteRepository.findByEcole(ecole);
    }

    public PeriodeNote save(PeriodeNote periodeNote) {
        return periodeNoteRepository.save(periodeNote);
    }

    public PeriodeNote findById(Integer id) {
        return periodeNoteRepository.findById(id).get();
    }

    public void deleteById(Integer id) {
        periodeNoteRepository.deleteById(id);
    }
}
