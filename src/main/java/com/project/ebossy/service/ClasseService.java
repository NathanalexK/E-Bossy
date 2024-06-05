package com.project.ebossy.service;


import com.project.ebossy.model.Classe;
import com.project.ebossy.model.Ecole;
import com.project.ebossy.model.Salle;
import com.project.ebossy.repository.ClasseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClasseService {

    private final ClasseRepository classeRepository;

    public ClasseService(ClasseRepository classeRepository) {
        this.classeRepository = classeRepository;
    }

    public List<Classe> findAll(Ecole ecole) {
        return classeRepository.findAllByEcole(ecole);
    }

    public Classe save(Classe classe) {
        return classeRepository.save(classe);
    }

    public void deleteById(int idClasse) {
        classeRepository.deleteById(idClasse);
    }

    public boolean isSalleOccuped(Salle salle) {
        return classeRepository.findByIdSalle(salle) != null;
    }


}
