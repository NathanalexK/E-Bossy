package com.project.ebossy.repository;

import com.project.ebossy.model.Classe;
import com.project.ebossy.model.Ecole;
import com.project.ebossy.model.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClasseRepository extends JpaRepository<Classe, Integer> {
    @Query("select c from Classe c where c.idEcole = ?1")
    List<Classe> findAllByEcole(Ecole idEcole);

    Classe findByIdSalle(Salle idSalle);
}