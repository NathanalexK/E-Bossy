package com.project.ebossy.repository;

import com.project.ebossy.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {
    @Query("select n from Note n where n.matiereProf = ?1")
    List<Note> findAllByMatiereProf(MatiereProf matiereProf);

    @Query("select n from Note n where n.matiereProf = ?1 and n.idPeriodeNote = ?2")
    List<Note> findAllByMatiereProfWithPeriodeNote(MatiereProf matiereProf, PeriodeNote idPeriodeNote);

    @Query("select n from Note n where n.idEleve = ?1 and n.idPeriodeNote.idAnneeScolaire = ?2")
    List<Note> findByIdEleveAndIdPeriodeNote_IdAnneeScolaire(Eleve idEleve, AnneeScolaire idAnneeScolaire);

    @Transactional
    @Modifying
    @Query("delete from Note n where n.idPeriodeNote = ?1")
    int deleteByIdPeriodeNote(PeriodeNote idPeriodeNote);
}