package com.project.ebossy.repository;

import com.project.ebossy.view.VPeriodeEcolageForDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface VPeriodeEcolageForDateRepository extends JpaRepository<VPeriodeEcolageForDate, Integer> {
    @Query("select v from VPeriodeEcolageForDate v where v.idEcole = ?1 and ?2 BETWEEN v.dateDebut AND v.dateFin")
    Optional<VPeriodeEcolageForDate> findByDate(Integer idEcole,LocalDate dates);
}