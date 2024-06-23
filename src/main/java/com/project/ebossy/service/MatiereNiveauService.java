package com.project.ebossy.service;


import com.project.ebossy.model.AnneeScolaire;
import com.project.ebossy.model.Matiere;
import com.project.ebossy.model.MatiereNiveau;
import com.project.ebossy.model.Niveau;
import com.project.ebossy.repository.AnneeScolaireRepository;
import com.project.ebossy.repository.MatiereNiveauRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MatiereNiveauService {

    private final MatiereNiveauRepository matiereNiveauRepository;
    private final AnneeScolaireRepository anneeScolaireRepository;

    public MatiereNiveauService(MatiereNiveauRepository matiereNiveauRepository, AnneeScolaireRepository anneeScolaireRepository) {
        this.matiereNiveauRepository = matiereNiveauRepository;
        this.anneeScolaireRepository = anneeScolaireRepository;
    }

    public MatiereNiveau save(MatiereNiveau matiereNiveau) {
        return matiereNiveauRepository.save(matiereNiveau);
    }

    public List<MatiereNiveau> findAllByNiveau(Niveau niveau) {
        return matiereNiveauRepository.findAllByNiveau(niveau);
    }

    public List<MatiereNiveau> findAllByAnneeScolaire(AnneeScolaire anneeScolaire) {
        return matiereNiveauRepository.findAllByAnneeScolaire(anneeScolaire);
    }

    public Map<Matiere, Integer> getCoefficientParMatiere(Niveau niveau){
        List<MatiereNiveau> matiereNiveauList = matiereNiveauRepository.findAllByNiveau(niveau);
        Map<Matiere, Integer> coefficientParMatiere = new HashMap<>();

        for(MatiereNiveau matiereNiveau : matiereNiveauList){
            coefficientParMatiere.put(matiereNiveau.getIdMatiere(), matiereNiveau.getCoefficient());
        }

        return coefficientParMatiere;
    }
}
