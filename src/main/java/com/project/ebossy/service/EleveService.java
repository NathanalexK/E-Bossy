package com.project.ebossy.service;

import com.project.ebossy.model.*;
import com.project.ebossy.repository.EleveAnneeScolaireRepository;
import com.project.ebossy.repository.EleveRepository;
import com.project.ebossy.repository.VEleveEcoleRepository;
import com.project.ebossy.view.VEleveEcole;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.Optional;

@Service
public class EleveService {
    private final EleveRepository eleveRepository;
    private final EleveAnneeScolaireRepository eleveAnneeScolaireRepository;
    private final VEleveEcoleService vEleveEcoleService;

    public EleveService(EleveRepository eleveRepository, EleveAnneeScolaireRepository eleveAnneeScolaireRepository, VEleveEcoleService vEleveEcoleService){
        this.eleveRepository = eleveRepository;
        this.eleveAnneeScolaireRepository = eleveAnneeScolaireRepository;
        this.vEleveEcoleService = vEleveEcoleService;
    }

    public Eleve findEleveById(Integer id){
        Optional<Eleve> eleve =  eleveRepository.findById(id);
        return eleve.orElse(null);
    }

    public List<Eleve> findByIdClasse(int classe){
        return eleveRepository.findByIdClasse(classe);
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

    public List<EleveAnneeScolaire> findAllByClasse(Classe classe){
        return eleveAnneeScolaireRepository.findAllByClasse(classe);
    }

    public List<EleveAnneeScolaire> findAllElevePasDeClasse(Niveau niveau){
        return eleveAnneeScolaireRepository.findAllElevePasDeClasseByNiveau(niveau);
    }


//    public EleveAnneeScolaire findClasseActuelEleve(Eleve eleve, Ecole ecole){
//        return eleve.getEleveAnneeScolaire().stream()
//                .filter(e -> e.getIdAnneeScolaire().getId().equals(ecole.getAnneeScolaire().getId()))
//                .findFirst()
//                .orElse(null);
//    }

//    public EleveAnneeScolaire findAll

    public Eleve getByNumero(Integer numero, Integer idclasse, Integer idEcole){
        VEleveEcole el = vEleveEcoleService.getByIdEleve(numero, idclasse, idEcole);
        Optional<Eleve> els =  getByIdEleve(el.getId());
        if (els.isPresent()){
            return  els.get();
        }
        return null;
    }

    public Eleve update(Integer id,Eleve eleve){
        Eleve eleve2 = getByIdEleve(id).get();
        eleve2.setIdSexe(eleve.getIdSexe());
        eleve2.setDateNaissance(eleve.getDateNaissance());
        eleve2.setIdTuteur(eleve.getIdTuteur());
        eleve2.setNom(eleve.getNom());
        eleve2.setPrenom(eleve.getPrenom());
        return eleveRepository.save(eleve2);
    }
}
