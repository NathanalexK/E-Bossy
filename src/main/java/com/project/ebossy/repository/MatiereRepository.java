package com.project.ebossy.repository;

import com.project.ebossy.model.AnneeScolaire;
import com.project.ebossy.model.Ecole;
import com.project.ebossy.model.Matiere;
import com.project.ebossy.model.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatiereRepository extends JpaRepository<Matiere, Integer> {
    @Query("select m from Matiere m where m.idEcole = ?1")
    List<Matiere> findAllByEcole(Ecole idEcole);

    @Query("select m from Matiere m inner join m.matiereNiveaux matiereNiveaux where matiereNiveaux.idNiveau = ?1")
    List<Matiere> findAllByNiveau(Niveau idNiveau);

    @Query("""
            select m from Matiere m inner join m.matiereNiveaux matiereNiveaux
            where matiereNiveaux.idNiveau.idAnneeScolaire = ?1""")
    List<Matiere> findByMatiereNiveaux_IdNiveau_IdAnneeScolaire(AnneeScolaire idAnneeScolaire);
}