package com.project.ebossy.repository;

import com.project.ebossy.model.AnneeScolaire;
import com.project.ebossy.model.Ecole;
import com.project.ebossy.model.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NiveauRepository extends JpaRepository<Niveau, Integer> {
    @Query("select n from Niveau n where n.idEcole.id = ?1 order by n.numero")
    List<Niveau> findByIdEcole(int idEcole);

    @Query("select n from Niveau n where n.id = ?1 order by n.numero")
    List<Niveau> findByEcole(Ecole idEcole);

    @Query("select n from Niveau n where n.idAnneeScolaire = ?1")
    List<Niveau> findByAnneeScolaire(AnneeScolaire idAnneeScolaire);
}