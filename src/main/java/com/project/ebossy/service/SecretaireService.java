package com.project.ebossy.service;

import com.project.ebossy.model.Ecole;
import com.project.ebossy.model.Secretaire;
import com.project.ebossy.repository.SecretaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SecretaireService {
    @Autowired
    private SecretaireRepository secretaireRepository;

    public List<Secretaire> findAll(int idEcole){
        return secretaireRepository.findByIdEcole(idEcole);
    }

    public  List<Secretaire> findByEcole(Ecole ecole) {
        return secretaireRepository.findByEcole(ecole);
    }

    public Secretaire findById(int idSecretaire) {
        Optional<Secretaire> secretaire = secretaireRepository.findById(idSecretaire);
        if(secretaire.isPresent()) return secretaire.get();
        return null;
    }

    public Secretaire save (Secretaire secretaire) {
        return secretaireRepository.save(secretaire);
    }

    public void deleteById(Integer id) {
        secretaireRepository.deleteById(id);
    }
}
