package com.project.ebossy.repository;

import com.project.ebossy.model.Ecole;
import com.project.ebossy.model.Professeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProfesseurRepository extends JpaRepository<Professeur, Integer> {
    @Query("select s from Professeur s where s.idEcole.id = ?1")
    List<Professeur> findByIdEcole(int idEcole);

    @Query("select s from Professeur s where s.idEcole.id = ?1")
    List<Professeur> findByEcole(Ecole Ecole);


}