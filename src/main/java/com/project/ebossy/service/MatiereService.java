package com.project.ebossy.service;

import com.project.ebossy.model.Ecole;
import com.project.ebossy.model.Matiere;
import com.project.ebossy.repository.ClasseRepository;
import com.project.ebossy.repository.MatiereRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

//    public Map<Matiere, List<Class>> getListeClasseParMatiere(Ecole ecole){
//        Map<Matiere, List<Class>> matiereClasseParMatiere = new HashMap<>();
//        List<Matiere> listeMatiere = matiereRepository.findAllByEcole(ecole);
//
//        for (Matiere matiere : listeMatiere){
//            matiereClasseParMatiere.put(matiere, );
//        }
//    }
}
