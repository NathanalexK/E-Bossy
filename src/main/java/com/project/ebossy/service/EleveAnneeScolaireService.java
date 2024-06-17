package com.project.ebossy.service;


import com.project.ebossy.model.EleveAnneeScolaire;
import com.project.ebossy.repository.EleveAnneeScolaireRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EleveAnneeScolaireService {


    private final EleveAnneeScolaireRepository eleveAnneeScolaireRepository;

    public EleveAnneeScolaireService(EleveAnneeScolaireRepository eleveAnneeScolaireRepository) {
        this.eleveAnneeScolaireRepository = eleveAnneeScolaireRepository;
    }

    public EleveAnneeScolaire save(EleveAnneeScolaire eleveAnneeScolaire) {
        return eleveAnneeScolaireRepository.save(eleveAnneeScolaire);
    }

    public EleveAnneeScolaire findById(Integer id) {
        Optional<EleveAnneeScolaire> eleveAnneeScolaire = eleveAnneeScolaireRepository.findById(id);
        return eleveAnneeScolaire.orElse(null);
    }
}
