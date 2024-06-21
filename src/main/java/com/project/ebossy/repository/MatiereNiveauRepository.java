package com.project.ebossy.repository;

import com.project.ebossy.model.MatiereNiveau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatiereNiveauRepository extends JpaRepository<MatiereNiveau, Integer> {
}