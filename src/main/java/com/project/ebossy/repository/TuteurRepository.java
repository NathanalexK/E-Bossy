package com.project.ebossy.repository;

import com.project.ebossy.model.Tuteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface TuteurRepository extends JpaRepository<Tuteur, Integer> {
    @Query("select t from Tuteur t where t.email = ?1 and t.mdp = ?2")
    Tuteur findByEmailAndPassword(String email, String mdp);

    @Query("select t from Tuteur t where t.email = ?1")
    Tuteur findByEmail(String email);
}