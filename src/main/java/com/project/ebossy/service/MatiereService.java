package com.project.ebossy.service;

import com.project.ebossy.model.Matiere;
import com.project.ebossy.repository.MatiereRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatiereService {

    private final MatiereRepository matiereRepository;

    public MatiereService(MatiereRepository matiereRepository) {
        this.matiereRepository = matiereRepository;
    }

    public List<Matiere> findAll() {
        return matiereRepository.findAll();
    }

    public Matiere save(Matiere matiere) {
        return matiereRepository.save(matiere);
    }
}
