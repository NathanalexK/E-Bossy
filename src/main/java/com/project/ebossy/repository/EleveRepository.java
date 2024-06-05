package com.project.ebossy.repository;

import com.project.ebossy.model.Eleve;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EleveRepository extends JpaRepository<Eleve, Integer> {
}