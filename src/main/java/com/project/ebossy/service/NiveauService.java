package com.project.ebossy.service;

import com.project.ebossy.model.AnneeScolaire;
import com.project.ebossy.model.Ecole;
import com.project.ebossy.model.Niveau;
import com.project.ebossy.repository.NiveauRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NiveauService {
    @Autowired
    private NiveauRepository niveauRepository;

    public List<Niveau> findAll(int idEcole) {
        return niveauRepository.findByIdEcole(idEcole);
    }

    public Niveau findById(int idNiveau) {
        Optional<Niveau> niveau = niveauRepository.findById(idNiveau);
        if(niveau.isPresent()) return niveau.get();
        return null;
    }

    public List<Niveau> findByEcole(Ecole ecole) {
        return niveauRepository.findByEcole(ecole);
    }

    public List<Niveau> findAllByAnneeScolaire(AnneeScolaire anneeScolaire) {
        return niveauRepository.findByAnneeScolaire(anneeScolaire);
    }

    public Niveau save(Niveau niveau) {
        return niveauRepository.save(niveau);
    }

    public void deleteById(Integer id) {
        niveauRepository.deleteById(id);
    }
}
