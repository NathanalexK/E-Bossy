package com.project.ebossy.repository;

import com.project.ebossy.model.Comportements;
import com.project.ebossy.model.Ecole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComportementsRepository extends JpaRepository<Comportements, Integer> {
    
    @Query("select s from Comportements s ")
    List<Comportements> findAll();
}
