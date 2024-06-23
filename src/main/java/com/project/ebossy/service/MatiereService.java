package com.project.ebossy.service;

import com.project.ebossy.model.AnneeScolaire;
import com.project.ebossy.model.Ecole;
import com.project.ebossy.model.Matiere;
import com.project.ebossy.model.Niveau;
import com.project.ebossy.repository.ClasseRepository;
import com.project.ebossy.repository.MatiereRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.Optional;

@Service
public class MatiereService {

    private final MatiereRepository matiereRepository;
    private final ClasseRepository classeRepository;

    public MatiereService(MatiereRepository matiereRepository, ClasseRepository classeRepository) {
        this.matiereRepository = matiereRepository;
        this.classeRepository = classeRepository;
    }

    public List<Matiere> findAllByEcole(Ecole ecole) {
        return matiereRepository.findAllByEcole(ecole);
    }

    public Matiere save(Matiere matiere) {
        return matiereRepository.save(matiere);
    }

    public Optional<Matiere> getMatiereById(Integer id){
        return matiereRepository.findById(id);
    }

    public List<Matiere> findAllMatiereByNiveau(Niveau niveau) {
        return matiereRepository.findAllByNiveau(niveau);
    }

    public List<Matiere> findAllByAnneeScolaire(AnneeScolaire anneeScolaire) {
        return matiereRepository.findByMatiereNiveaux_IdNiveau_IdAnneeScolaire(anneeScolaire);
    }

//    public List<Matiere> findAllByAnneeScolaire(AnneeScolaire anneeScolaire) {
//        return matiereRepository.find
//    }

//    public Matiere update(Integer id,Matiere matiere){
//        Matiere matiere2 = getMatiereById(id).get();
//        // matiere2.setId(id);
//        matiere2.setNomMatiere(matiere.getNomMatiere());
//        matiere2.setCoefficient(matiere.getCoefficient());
//        matiere2.setIdEcole(matiere.getIdEcole());
//        matiere2.setIdNiveau(matiere.getIdNiveau());
//        return matiereRepository.save(matiere2);
//    }

    public Matiere update(Matiere matiere) {
        return matiereRepository.save(matiere);
    }

//    public Map<Matiere, List<Class>> getListeClasseParMatiere(Ecole ecole){
//        Map<Matiere, List<Class>> matiereClasseParMatiere = new HashMap<>();
//        List<Matiere> listeMatiere = matiereRepository.findAllByEcole(ecole);
//
//        for (Matiere matiere : listeMatiere){
//            matiereClasseParMatiere.put(matiere, );
//        }
//    }
}
