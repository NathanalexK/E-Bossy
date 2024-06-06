package com.project.ebossy.service;


import com.project.ebossy.model.AnneeScolaire;
import com.project.ebossy.model.Ecole;
import com.project.ebossy.repository.AnneeScolaireRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnneeScolaireService {

    private final AnneeScolaireRepository anneeScolaireRepository;

    public AnneeScolaireService(AnneeScolaireRepository anneeScolaireRepository) {
        this.anneeScolaireRepository = anneeScolaireRepository;
    }

    public List<AnneeScolaire> findAllByEcole(Ecole ecole) {
        return anneeScolaireRepository.findAllByEcole(ecole);
    }
}
