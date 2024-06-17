package com.project.ebossy.service;


import com.project.ebossy.model.*;
import com.project.ebossy.repository.MatiereProfRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MatiereProfService {

    private final MatiereProfRepository matiereProfRepository;
    private final MatiereService matiereService;

    public MatiereProfService(MatiereProfRepository matiereProfRepository, MatiereService matiereService) {
        this.matiereProfRepository = matiereProfRepository;
        this.matiereService = matiereService;
    }

    public List<MatiereProf> findAllByEcole(Ecole ecole) {
        return matiereProfRepository.findAllByEcole(ecole);
    }

    public List<MatiereProf> findAllByClasse(Classe classe) {
        return matiereProfRepository.findAllByClasse(classe);
    }

    public List<MatiereProf> findAllByProf(Professeur professeur) {
        return matiereProfRepository.findAllByProfesseur(professeur);
    }

    public Map<Matiere, Professeur> findMatiereProfByClasse(Classe classe) {
        List<Matiere> matieres = matiereService.findAllMatiereByNiveau(classe.getIdNiveau());
        List<MatiereProf> matiereProfList = findAllByClasse(classe);
        Map<Matiere, Professeur> matiereProfMap = new LinkedHashMap<>();

        for (Matiere matiere : matieres) {
            matiereProfMap.put(matiere, null);
        }

        for (MatiereProf matiereProf : matiereProfList) {
            matiereProfMap.put(matiereProf.getIdMatiere(), matiereProf.getIdProf());
        }

        return matiereProfMap;
    }

    public MatiereProf findMatiereProf(Classe classe, Matiere matiere) {
        return matiereProfRepository.findMatiereProf(classe, matiere);
    }

    public MatiereProf save(MatiereProf matiereProf) {
        return matiereProfRepository.save(matiereProf);
    }

//    public Map<Matiere, Professeur> findMatiereProfByClasse(Classe classe) {
//        List<Matiere> matiereList = matiereService.findAllMatiereByNiveau(classe.getIdNiveau());
//        List<M>
//
//
//    }
}
