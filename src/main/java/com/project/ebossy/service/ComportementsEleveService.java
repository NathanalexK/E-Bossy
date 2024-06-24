package com.project.ebossy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.ebossy.repository.ComportementsEleveRepository;
import com.project.ebossy.view.ComportementsEleve;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class ComportementsEleveService {

    private final ComportementsEleveRepository comportementsEleveRepository;

    @Autowired
    public ComportementsEleveService(ComportementsEleveRepository comportementsEleveRepository){
        this.comportementsEleveRepository = comportementsEleveRepository;
    }

    public List<ComportementsEleve> findAll(){
        return comportementsEleveRepository.findAll();
    }

    @Transactional
    public void updateComportementsEleve(int eleveId, int newComportementId) {
        ComportementsEleve comportementsEleve = comportementsEleveRepository.findById(eleveId).orElse(null);
        if (comportementsEleve != null) {
            comportementsEleve.setComp(newComportementId); // Mettez à jour le comportement de l'élève
            comportementsEleveRepository.save(comportementsEleve);
        } else {
            throw new IllegalArgumentException("Élève non trouvé avec l'ID : " + eleveId);
        }
    }
}
