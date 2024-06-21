package com.project.ebossy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.ebossy.view.ComportementsEleve;
import java.util.List;

@Repository
public interface ComportementsEleveRepository extends JpaRepository<ComportementsEleve, Integer> {
   
   @Query("select s from ComportementsEleve s")
   List<ComportementsEleve> findAll();
}

