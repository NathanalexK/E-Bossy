package com.project.ebossy.repository;

import com.project.ebossy.model.AnneeScolaire;
import com.project.ebossy.model.Ecole;
import com.project.ebossy.model.Eleve;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface EleveRepository extends JpaRepository<Eleve, Integer> {
    @Query("select m from Eleve m where m.idEcole = ?1")
    List<Eleve> findAllByEcole(Ecole idEcole);

}