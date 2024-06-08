package com.project.ebossy.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.ebossy.model.PeriodeEcolage;

public interface PeriodeEcolageRepository extends JpaRepository<PeriodeEcolage, Integer> {
    
    @Query("SELECT p FROM PeriodeEcolage p " +
           "WHERE :date BETWEEN p.dateDebut AND p.dateFin")
    PeriodeEcolage getByAnneScolaire(LocalDate date);
}
