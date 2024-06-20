package com.project.ebossy.repository;

import com.project.ebossy.model.MatiereProf;
import com.project.ebossy.model.Note;
import com.project.ebossy.model.PeriodeNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {
    @Query("select n from Note n where n.matiereProf = ?1")
    List<Note> findAllByMatiereProf(MatiereProf matiereProf);

    @Query("select n from Note n where n.matiereProf = ?1 and n.idPeriodeNote = ?2")
    List<Note> findAllByMatiereProfWithPeriodeNote(MatiereProf matiereProf, PeriodeNote idPeriodeNote);
}