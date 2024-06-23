package com.project.ebossy.service;


import com.project.ebossy.model.Matiere;
import com.project.ebossy.model.MatiereNiveau;
import com.project.ebossy.model.Niveau;
import com.project.ebossy.repository.MatiereNiveauRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MatiereNiveauService {

    private final MatiereNiveauRepository matiereNiveauRepository;

    public MatiereNiveauService(MatiereNiveauRepository matiereNiveauRepository) {
        this.matiereNiveauRepository = matiereNiveauRepository;
    }

    public MatiereNiveau save(MatiereNiveau matiereNiveau) {
        return matiereNiveauRepository.save(matiereNiveau);
    }

    public List<MatiereNiveau> findAllByNiveau(Niveau niveau) {
        return matiereNiveauRepository.findAllByNiveau(niveau);
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
