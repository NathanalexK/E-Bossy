package com.project.ebossy.service;


import com.project.ebossy.model.Ecole;
import com.project.ebossy.repository.EcoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EcoleService {

    private final EcoleRepository ecoleRepository;

    public EcoleService(EcoleRepository ecoleRepository) {
        this.ecoleRepository = ecoleRepository;
    }

    public Optional<Ecole> getEcoleById(int id) {
        return ecoleRepository.findById(id);
    }
}
