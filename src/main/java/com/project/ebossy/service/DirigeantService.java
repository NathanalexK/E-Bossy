package com.project.ebossy.service;

import com.project.ebossy.model.Dirigeant;
import com.project.ebossy.repository.DirigeantRepository;
import org.springframework.stereotype.Service;

@Service
public class DirigeantService {

    private final DirigeantRepository dirigeantRepository;

    public DirigeantService(DirigeantRepository dirigeantRepository) {
        this.dirigeantRepository = dirigeantRepository;
    }

    public Dirigeant authenticate(String identifiant, String password) {
        return dirigeantRepository.findByIdentifiantAndMotDePasse(identifiant, password);
    }
}
