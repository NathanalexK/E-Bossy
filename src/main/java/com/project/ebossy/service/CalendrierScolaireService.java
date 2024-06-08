package com.project.ebossy.service;

import com.project.ebossy.model.Ecole;
import com.project.ebossy.repository.CalendrierScolaireRepository;
import com.project.ebossy.view.CalendrierScolaire;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendrierScolaireService {


    private final CalendrierScolaireRepository calendrierScolaireRepository;

    public CalendrierScolaireService(CalendrierScolaireRepository calendrierScolaireRepository) {
        this.calendrierScolaireRepository = calendrierScolaireRepository;
    }

    public List<CalendrierScolaire> getCalendrierScolaireActuel(Ecole ecole) {
        return calendrierScolaireRepository.findAllCalendrierScolaire(ecole, ecole.getAnneeScolaire());
    }
}
