package com.project.ebossy.repository;

import com.project.ebossy.model.Convocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConvocationRepository extends JpaRepository<Convocation, Integer> {

    @Query("SELECT c FROM Convocation c WHERE c.idEcole.id = ?1 AND c.idEleveAnneeScolaire.idClasse = ?2")
    List<Convocation> findAllByClasse(int idEcole, int idClasse);

}
