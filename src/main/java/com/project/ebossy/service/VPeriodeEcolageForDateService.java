package com.project.ebossy.service;

import com.project.ebossy.model.Ecole;
import com.project.ebossy.repository.PeriodeEcolageRepository;
import com.project.ebossy.repository.VPeriodeEcolageForDateRepository;
import com.project.ebossy.view.VPeriodeEcolageForDate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
@Service
public class VPeriodeEcolageForDateService {

    private final VPeriodeEcolageForDateRepository vPeriodeEcolageForDateRepository;

    public VPeriodeEcolageForDateService(VPeriodeEcolageForDateRepository vPeriodeEcolageForDateRepository) {
        this.vPeriodeEcolageForDateRepository = vPeriodeEcolageForDateRepository;
    }
    public VPeriodeEcolageForDate getByDate(Integer idEcole, LocalDate dates) {
        Optional<VPeriodeEcolageForDate> p = vPeriodeEcolageForDateRepository.findByDate(idEcole, dates);
        if (p.isPresent()){
            return p.get();
        }
        return null;
    }
}
