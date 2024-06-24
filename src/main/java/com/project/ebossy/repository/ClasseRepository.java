package com.project.ebossy.repository;

import com.project.ebossy.model.AnneeScolaire;
import com.project.ebossy.model.Classe;
import com.project.ebossy.model.Ecole;
import com.project.ebossy.model.Salle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClasseRepository extends JpaRepository<Classe, Integer> {
    @Query("select c from Classe c where c.idEcole = ?1")
    List<Classe> findAllByEcole(Ecole idEcole);

    Classe findByIdSalle(Salle idSalle);

    List<Classe> findByIdEcole(Ecole idEcole);

    @Query("select c from Classe c where c.idEcole = ?1")
    Classe findOneByEcole(Ecole idEcole);

    @Query("select c from Classe c where c.idEcole.anneeScolaire = ?1")
    List<Classe> findAllByAnneeScolaire(AnneeScolaire anneeScolaire);
//    @Query("select c from Classe c where c.idEcole.id = :idEcole")
//    Optional<Classe> findFirstClasseByEcole(@Param("idEcole") int idEcole, Pageable pageable);

}