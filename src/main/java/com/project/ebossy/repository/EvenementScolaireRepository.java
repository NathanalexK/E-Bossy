package com.project.ebossy.repository;

import com.project.ebossy.model.EvenementScolaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvenementScolaireRepository extends JpaRepository<EvenementScolaire, Integer> {
}