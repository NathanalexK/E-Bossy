package com.project.ebossy.repository;

import com.project.ebossy.model.Ecole;
import com.project.ebossy.model.Professeur;
import com.project.ebossy.model.Secretaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SecretaireRepository extends JpaRepository<Secretaire, Integer> {
    @Query("select s from Secretaire s where s.idEcole.id = ?1")
    List<Secretaire> findByIdEcole(int idEcole);

    @Query("select s from Secretaire s where s.idEcole.id = ?1")
    List<Secretaire> findByEcole(Ecole Ecole);
}