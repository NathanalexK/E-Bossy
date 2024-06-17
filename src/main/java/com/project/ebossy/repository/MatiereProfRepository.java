package com.project.ebossy.repository;

import com.project.ebossy.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatiereProfRepository extends JpaRepository<MatiereProf, Integer> {

  @Query("select p from MatiereProf p where p.idClasse.idEcole = ?1")
  List<MatiereProf> findAllByEcole(Ecole idEcole);

  @Query("select p from MatiereProf p where p.idClasse = ?1")
  List<MatiereProf> findAllByClasse(Classe idClasse);

  @Query("select p from MatiereProf p where p.idProf = ?1")
  List<MatiereProf> findAllByProfesseur(Professeur idProf);

  @Query("select m from MatiereProf m where m.idClasse = ?1 and m.idMatiere = ?2")
  MatiereProf findMatiereProf(Classe idClasse, Matiere idMatiere);
}