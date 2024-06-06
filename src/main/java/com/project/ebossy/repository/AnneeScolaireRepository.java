package com.project.ebossy.repository;

import com.project.ebossy.model.AnneeScolaire;
import com.project.ebossy.model.Ecole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnneeScolaireRepository extends JpaRepository<AnneeScolaire, Integer> {

    @Query("select a from AnneeScolaire a where a.idEcole = ?1")
    List<AnneeScolaire> findAllByEcole(Ecole idEcole);
}