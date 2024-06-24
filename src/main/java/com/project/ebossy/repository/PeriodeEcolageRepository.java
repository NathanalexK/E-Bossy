package com.project.ebossy.repository;

import java.time.LocalDate;
import java.util.List;

import com.project.ebossy.model.PeriodeEcolage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PeriodeEcolageRepository extends JpaRepository<PeriodeEcolage, Integer> {
    
    @Query("SELECT p FROM PeriodeEcolage p " +
           "WHERE :date BETWEEN p.dateDebut AND p.dateFin")
    PeriodeEcolage getByAnneScolaire(LocalDate date);

    @Query("SELECT pe " +
            "FROM EleveAnneeScolaire eas " +
            "JOIN PeriodeEcolage pe ON eas.idAnneeScolaire = pe.idAnneeScolaire " +
            "LEFT JOIN PayeEcolage p ON eas.id = p.idEleveAnneeScolaire.id AND pe.id = p.idPeriodeEcolage.id " +
            "WHERE p.id IS NULL and eas.id = ?1")
    List<PeriodeEcolage> getByNonPayerEleve(int idEleve);
}
