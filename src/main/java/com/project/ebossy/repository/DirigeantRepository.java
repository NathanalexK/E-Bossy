package com.project.ebossy.repository;

import com.project.ebossy.model.Dirigeant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DirigeantRepository extends JpaRepository<Dirigeant, Integer> {
    @Query("select d from Dirigeant d where d.identifiant = ?1 and d.mdp = ?2")
    Dirigeant findByIdentifiantAndMotDePasse(String email, String mdp);
}