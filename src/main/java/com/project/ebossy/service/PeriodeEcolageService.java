package com.project.ebossy.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.project.ebossy.model.PeriodeEcolage;
import com.project.ebossy.repository.PeriodeEcolageRepository;

@Service
public class PeriodeEcolageService {

    private final PeriodeEcolageRepository periodeEcolageRepository;

    public PeriodeEcolageService(PeriodeEcolageRepository periodeEcolageRepository) {
        this.periodeEcolageRepository = periodeEcolageRepository;
    }

    public PeriodeEcolage getByDate(LocalDate date) {
        return periodeEcolageRepository.getByAnneScolaire(date);
    }
}

