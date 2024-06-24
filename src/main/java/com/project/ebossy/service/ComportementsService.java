package com.project.ebossy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.ebossy.model.Comportements;
import com.project.ebossy.repository.ComportementsRepository;

import java.util.List;

@Service
public class ComportementsService {

    private final ComportementsRepository comportementsRepository;

    @Autowired
    public ComportementsService(ComportementsRepository comportementsRepository) {
        this.comportementsRepository = comportementsRepository;
    }

    public List<Comportements> findAll() {
        return comportementsRepository.findAll();
    }

    public void save(Comportements comportements) {
        comportementsRepository.save(comportements);
    }

    public void deleteById(Integer id) {
        comportementsRepository.deleteById(id);
    }
}
