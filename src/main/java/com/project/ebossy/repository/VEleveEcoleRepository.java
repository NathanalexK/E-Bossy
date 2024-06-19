package com.project.ebossy.repository;

import com.project.ebossy.view.VEleveEcole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface VEleveEcoleRepository extends JpaRepository<VEleveEcole, Integer> {

    @Query("select e from VEleveEcole e where e.numero= ?1 and e.idClasse = ?2 and e.idEcole = ?3 ")
    Optional<VEleveEcole> findByNumero(Integer numero, Integer idclasse, Integer idEcole);
}