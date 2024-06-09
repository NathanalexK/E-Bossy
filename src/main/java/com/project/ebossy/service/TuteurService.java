package com.project.ebossy.service;


import com.project.ebossy.model.Tuteur;
import com.project.ebossy.repository.TuteurRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TuteurService {

    private final TuteurRepository tuteurRepository;

    public TuteurService(TuteurRepository tuteurRepository) {
        this.tuteurRepository = tuteurRepository;
    }

    public List<Tuteur> findAll() {
        return tuteurRepository.findAll();
    }

    public Tuteur findById(int id) {
        Optional<Tuteur> tuteur = tuteurRepository.findById(id);
        if (tuteur.isPresent()) {
            return tuteur.get();
        }
        return null;
    }

    public Tuteur authenticate(String email, String password) {
        return tuteurRepository.findByEmailAndPassword(email, password);
    }

    public Tuteur save(Tuteur tuteur) {
        return tuteurRepository.save(tuteur);
    }
}
