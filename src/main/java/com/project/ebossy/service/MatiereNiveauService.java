package com.project.ebossy.service;


import com.project.ebossy.model.MatiereNiveau;
import com.project.ebossy.repository.MatiereNiveauRepository;
import org.springframework.stereotype.Service;

@Service
public class MatiereNiveauService {

    private final MatiereNiveauRepository matiereNiveauRepository;

    public MatiereNiveauService(MatiereNiveauRepository matiereNiveauRepository) {
        this.matiereNiveauRepository = matiereNiveauRepository;
    }

    public MatiereNiveau save(MatiereNiveau matiereNiveau) {
        return matiereNiveauRepository.save(matiereNiveau);
    }
}
