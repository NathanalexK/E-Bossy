package com.project.ebossy.service;


import com.project.ebossy.model.EvenementScolaire;
import com.project.ebossy.repository.EvenementScolaireRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvenementScolaireService {

    private final EvenementScolaireRepository evenementScolaireRepository;

    public EvenementScolaireService(EvenementScolaireRepository evenementScolaireRepository) {
        this.evenementScolaireRepository = evenementScolaireRepository;
    }

    public EvenementScolaire save(EvenementScolaire evenementScolaire) {
        return evenementScolaireRepository.save(evenementScolaire);
    }
//    public List<E>
}
