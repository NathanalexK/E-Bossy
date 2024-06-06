package com.project.ebossy.repository;

import com.project.ebossy.model.Ecole;
import com.project.ebossy.model.PeriodeNote;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PeriodeNoteRepository extends JpaRepository<PeriodeNote, Integer> {
    @Query("select p from PeriodeNote p where p.idEcole = ?1")
    List<PeriodeNote> findByEcole(Ecole idEcole);
}