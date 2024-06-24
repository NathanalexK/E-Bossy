package com.project.ebossy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.ebossy.view.ComportementsEleve;
import java.util.List;

@Repository
public interface ComportementsEleveRepository extends JpaRepository<ComportementsEleve, Integer> {
   // Utilisez la méthode par défaut findAll
   List<ComportementsEleve> findAll();
}
