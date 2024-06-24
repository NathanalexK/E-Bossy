package com.project.ebossy.service;


import com.project.ebossy.model.AnneeScolaire;
import com.project.ebossy.model.Classe;
import com.project.ebossy.model.EleveAnneeScolaire;
import com.project.ebossy.model.Sexe;
import com.project.ebossy.repository.EleveAnneeScolaireRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EleveAnneeScolaireService {


    private final EleveAnneeScolaireRepository eleveAnneeScolaireRepository;

    public EleveAnneeScolaireService(EleveAnneeScolaireRepository eleveAnneeScolaireRepository) {
        this.eleveAnneeScolaireRepository = eleveAnneeScolaireRepository;
    }

    public EleveAnneeScolaire save(EleveAnneeScolaire eleveAnneeScolaire) {
        return eleveAnneeScolaireRepository.save(eleveAnneeScolaire);
    }

    public EleveAnneeScolaire findById(Integer id) {
        Optional<EleveAnneeScolaire> eleveAnneeScolaire = eleveAnneeScolaireRepository.findById(id);
        return eleveAnneeScolaire.orElse(null);
    }

    public List<EleveAnneeScolaire> findAllByClasse(Classe classe) {
        return eleveAnneeScolaireRepository.findAllByClasse(classe);
    }

    public Page<EleveAnneeScolaire> findAllByClasse(Classe classe, int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return eleveAnneeScolaireRepository.findAllByClassePageable(classe, pageable);
    }

    public Page<EleveAnneeScolaire> searchEleveAnneeScolaire(
            AnneeScolaire anneeScolaire,
            String nom,
            String prenom,
            Sexe idSexe,
            LocalDate debut,
            LocalDate fin,
            int page
    ) {
        Pageable pageable = PageRequest.of(page, 10);
        return eleveAnneeScolaireRepository.findAllByCriteria(anneeScolaire, nom, prenom, idSexe, debut, fin, pageable);
    }
}
