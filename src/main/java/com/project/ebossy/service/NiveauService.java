package com.project.ebossy.service;

import com.project.ebossy.model.Niveau;
import com.project.ebossy.repository.NiveauRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NiveauService {
    @Autowired
    private NiveauRepository niveauRepository;

    public Niveau save(Niveau niveau) {
        return niveauRepository.save(niveau);
    }
}
