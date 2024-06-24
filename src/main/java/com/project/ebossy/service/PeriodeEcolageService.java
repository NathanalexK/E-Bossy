package com.project.ebossy.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.project.ebossy.model.PeriodeEcolage;
import com.project.ebossy.view.VPeriodeEcolageForDate;
import org.springframework.stereotype.Service;

import com.project.ebossy.repository.PeriodeEcolageRepository;

@Service
public class PeriodeEcolageService {

    private final PeriodeEcolageRepository periodeEcolageRepository;
    private final VPeriodeEcolageForDateService vPeriodeEcolageForDateService;

    public PeriodeEcolageService(VPeriodeEcolageForDateService vPeriodeEcolageForDateService,PeriodeEcolageRepository periodeEcolageRepository) {
        this.periodeEcolageRepository = periodeEcolageRepository;
        this.vPeriodeEcolageForDateService = vPeriodeEcolageForDateService;
    }

    public PeriodeEcolage getByDate(Integer idEcole, LocalDate dates){
        VPeriodeEcolageForDate vp = vPeriodeEcolageForDateService.getByDate(idEcole, dates);
        Optional<PeriodeEcolage> pr = getById(vp.getId());
        if (pr.isPresent()){
            return  pr.get();
        }
        return null;
    }

    public List<PeriodeEcolage> getByNonPayerEleve(Integer idEleve){
        return periodeEcolageRepository.getByNonPayerEleve(idEleve);
    }

    public Optional<PeriodeEcolage> getById(Integer id){
        return periodeEcolageRepository.findById(id);
    }


}

