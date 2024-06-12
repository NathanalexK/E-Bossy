package com.project.ebossy.repository;

import com.project.ebossy.model.Sexe;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SexeRepository extends JpaRepository<Sexe, Integer> {
    @Query("select s from Sexe s")
    List<Sexe> findAll();
}