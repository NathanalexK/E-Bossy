package com.project.ebossy.service;


import com.project.ebossy.model.Ecole;
import com.project.ebossy.model.Professeur;
import com.project.ebossy.repository.ProfesseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesseurService {
    @Autowired
    private ProfesseurRepository professeurRepository;

    public List<Professeur> findAll(int idEcole){
        return professeurRepository.findByIdEcole(idEcole);
    }

    public  List<Professeur> findByEcole(Ecole ecole) {
        return professeurRepository.findByEcole(ecole);
    }

    public Professeur findById(int idProfesseur) {
        Optional<Professeur> professeur = professeurRepository.findById(idProfesseur);
        if(professeur.isPresent()) return professeur.get();
        return null;
    }

    public Professeur save (Professeur professeur) {
        return professeurRepository.save(professeur);
    }

    public void deleteById(Integer id) {
        professeurRepository.deleteById(id);
    }
}
