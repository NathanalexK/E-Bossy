package com.project.ebossy.service;

import com.project.ebossy.model.Ecole;
import com.project.ebossy.model.Eleve;
import com.project.ebossy.repository.EleveRepository;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.Optional;

@Service
public class EleveService {
    private final EleveRepository eleveRepository;
    private final Eleve eleve;

    public EleveService(EleveRepository eleveRepository,Eleve eleve){
        this.eleveRepository = eleveRepository;
        this.eleve = eleve;
    }

    public Optional<Eleve> getEleveById(Integer id){
        return eleveRepository.findById(id);
    }

    public List<Eleve> findAllByEcole(Ecole ecole){
        return eleveRepository.findAllByEcole(ecole);
    }

    public Eleve save(Eleve eleve){
        return eleveRepository.save(eleve);
    }

    public Optional<Eleve> getByIdEleve(Integer id){
        return eleveRepository.findById(id);
    }

    public Eleve update(Integer id,Eleve eleve){
        Eleve eleve2 = getByIdEleve(id).get();
        eleve2.setIdClasse(eleve.getIdClasse());
        eleve2.setIdSexe(eleve.getIdSexe());
        eleve2.setDateNaissance(eleve.getDateNaissance());
        eleve2.setEtablissementOrigine(eleve.getEtablissementOrigine());
        eleve2.setIdTuteur(eleve.getIdTuteur());
        eleve2.setNom(eleve.getNom());
        eleve2.setPrenom(eleve.getPrenom());
        return eleveRepository.save(eleve2);
    }
}
