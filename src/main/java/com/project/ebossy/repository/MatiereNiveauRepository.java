package com.project.ebossy.repository;

import com.project.ebossy.model.AnneeScolaire;
import com.project.ebossy.model.MatiereNiveau;
import com.project.ebossy.model.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatiereNiveauRepository extends JpaRepository<MatiereNiveau, Integer> {
    @Query("select m from MatiereNiveau m where m.idNiveau = ?1")
    List<MatiereNiveau> findAllByNiveau(Niveau idNiveau);

    @Query("select m from MatiereNiveau m where m.idNiveau.idAnneeScolaire = ?1")
    List<MatiereNiveau> findAllByAnneeScolaire(AnneeScolaire idAnneeScolaire);
}