package com.project.ebossy.repository;

import com.project.ebossy.model.AnneeScolaire;
import com.project.ebossy.model.Ecole;
import com.project.ebossy.view.CalendrierScolaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendrierScolaireRepository extends JpaRepository<CalendrierScolaire, Long> {
    @Query("select c from CalendrierScolaire c where c.idEcole = ?1 and c.idAnneeScolaire = ?2")
    List<CalendrierScolaire> findAllCalendrierScolaire(Ecole idEcole, AnneeScolaire idAnneeScolaire);
}