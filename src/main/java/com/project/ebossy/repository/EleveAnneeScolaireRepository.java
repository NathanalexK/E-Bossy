package com.project.ebossy.repository;

import com.project.ebossy.model.Classe;
import com.project.ebossy.model.EleveAnneeScolaire;
import com.project.ebossy.model.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EleveAnneeScolaireRepository extends JpaRepository<EleveAnneeScolaire, Integer> {
    @Query("select e from EleveAnneeScolaire e where e.idClasse = ?1")
    List<EleveAnneeScolaire> findAllByClasse(Classe idClasse);

    @Query("select e from EleveAnneeScolaire e where e.idNiveau = ?1 and e.idClasse is null ")
    List<EleveAnneeScolaire> findAllElevePasDeClasseByNiveau(Niveau idNiveau);
}