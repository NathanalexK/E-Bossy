package com.project.ebossy.repository;

import com.project.ebossy.model.Salle;
import com.project.ebossy.view.EleveNonPayeEcolage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface EleveNonPayeEcolageRepository extends JpaRepository<EleveNonPayeEcolage, Integer> {
    @Query("select e from EleveNonPayeEcolage e where e.idEcole = ?1")
    List<EleveNonPayeEcolage> findAllByEcole(int idEcole);

    @Query("select e from EleveNonPayeEcolage e where e.idEcole = ?1 and  e.idClasse = ?2")
    List<EleveNonPayeEcolage> findAllByClasse(int idEcole,int idClasse);
}