package com.project.ebossy.service;

import com.project.ebossy.model.Ecole;
import com.project.ebossy.repository.CalendrierScolaireRepository;
import com.project.ebossy.view.CalendrierScolaire;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class CalendrierScolaireService {


    private final CalendrierScolaireRepository calendrierScolaireRepository;

    public CalendrierScolaireService(CalendrierScolaireRepository calendrierScolaireRepository) {
        this.calendrierScolaireRepository = calendrierScolaireRepository;
    }

    public List<CalendrierScolaire> getCalendrierScolaireActuel(Ecole ecole) {
        return calendrierScolaireRepository.findAllCalendrierScolaire(ecole, ecole.getAnneeScolaire());
    }

    @Transactional
    public Map<Integer, List<CalendrierScolaire>> getCalendrierScolaireActuelGrouppedByStatus(Ecole ecole) {
        List<CalendrierScolaire> calendrierScolaire = getCalendrierScolaireActuel(ecole);
        Map<Integer, List<CalendrierScolaire>> map = new HashMap<>();

        map.put(1, calendrierScolaire.stream().filter(e -> e.getStatus().getId() == 1).sorted(Comparator.comparingInt(CalendrierScolaire::getDateDiff)).toList());
        map.put(2, calendrierScolaire.stream().filter(e -> e.getStatus().getId() == 2).toList());
        map.put(3, calendrierScolaire.stream().filter(e -> e.getStatus().getId() == 3).toList());

        return map;
    }

}
