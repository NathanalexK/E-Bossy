package com.project.ebossy.service;


import com.project.ebossy.model.*;
import com.project.ebossy.repository.ClasseRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClasseService {

    private final ClasseRepository classeRepository;

    public ClasseService(ClasseRepository classeRepository) {
        this.classeRepository = classeRepository;
    }

    public List<Classe> findAll(Ecole ecole) {
        return classeRepository.findAllByEcole(ecole);
    }

    public Classe findById(Integer id) {
        Optional<Classe> classe = classeRepository.findById(id);
        return classe.orElse(null);
    }

    public List<Classe> findAllByAnneeScolaire(AnneeScolaire anneeScolaire) {
        return classeRepository.findAllByAnneeScolaire(anneeScolaire);
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

//    public List<Classe> findClasseByProfesseur(Professeur professeur, AnneeScolaire anneeScolaire) {
//        return List<Pr>;
//    }

//    public Classe findOneClasse(Ecole ecole) {
//        Pageable page = PageRequest.of(1, 1);
//        Optional<Classe> classe = classeRepository.findFirstClasseByEcole(ecole.getId(), page);
//        if(classe.isPresent()) {
//            return classe.get();
//        }
//        return null;
//    }
//


}
