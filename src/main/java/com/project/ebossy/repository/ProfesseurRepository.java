package com.project.ebossy.repository;

import com.project.ebossy.model.Ecole;
import com.project.ebossy.model.Professeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfesseurRepository extends JpaRepository<Professeur, Integer> {
    @Query("select p from Professeur p where p.idEcole = ?1")
    List<Professeur> findAllByEcole(Ecole idEcole);

    @Query("select p from Professeur p where p.identifiant = ?1")
    Professeur findByIdentifiant(String identifiant);

    @Query("select p from Professeur p where p.identifiant = ?1 and p.mdp = ?2")
    Professeur findByIdentifiantAndMotDePasse(String identifiant, String mdp);
}