package com.project.ebossy.repository;

import com.project.ebossy.model.Sexe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SexeRepository extends JpaRepository<Sexe, Integer> {
}