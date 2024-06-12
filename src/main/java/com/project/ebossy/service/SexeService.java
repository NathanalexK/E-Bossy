package com.project.ebossy.service;


import com.project.ebossy.model.Sexe;
import com.project.ebossy.repository.SexeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SexeService {
    private final SexeRepository sexeRepository;

    public SexeService(SexeRepository sexeRepository) {
        this.sexeRepository = sexeRepository;
    }

    public List<Sexe> findAll() {
        return sexeRepository.findAll();
    }
}
