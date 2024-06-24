package com.project.ebossy.repository;

import com.project.ebossy.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface EleveAnneeScolaireRepository extends JpaRepository<EleveAnneeScolaire, Integer> {
    @Query("select e from EleveAnneeScolaire e where e.idClasse = ?1")
    List<EleveAnneeScolaire> findAllByClasse(Classe idClasse);

    @Query("select e from EleveAnneeScolaire e where e.idClasse = ?1")
    Page<EleveAnneeScolaire> findAllByClassePageable(Classe idClasse, Pageable pageable);

    @Query("select e from EleveAnneeScolaire e where e.idNiveau = ?1 and e.idClasse is null ")
    List<EleveAnneeScolaire> findAllElevePasDeClasseByNiveau(Niveau idNiveau);

    @Query("select e from EleveAnneeScolaire e where e.idAnneeScolaire = ?1")
    List<EleveAnneeScolaire> findAllByNiveau(AnneeScolaire as, Pageable pageable);

    @Query(
            "select e from EleveAnneeScolaire e where " +
                    "(e.idAnneeScolaire = :idAnneeScolaire) AND" +
                    "(:nom IS NULL OR e.idEleve.nom ILIKE  %:nom%) AND " +
                    "(:prenom IS NULL or e.idEleve.prenom ILIKE %:prenom%) AND" +
                    "(:idSexe IS NULL or e.idEleve.idSexe = :idSexe) AND " +
                    "(coalesce( :dateDebut,null) IS NULL or e.idEleve.dateNaissance >= :dateDebut) AND" +
                    "(coalesce( :dateFin,null) IS NULL or e.idEleve.dateNaissance <= :dateFin)"
    )
    Page<EleveAnneeScolaire> findAllByCriteria(
            @Param("idAnneeScolaire") AnneeScolaire anneeScolaire,
            @Param("nom") String nom,
            @Param("prenom") String prenom,
            @Param("idSexe") Sexe idSexe,
            @Param("dateDebut") LocalDate dateDebut,
            @Param("dateFin") LocalDate dateFin,
            Pageable pageable
    );
}