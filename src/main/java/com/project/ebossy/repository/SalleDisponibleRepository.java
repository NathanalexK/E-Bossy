package com.project.ebossy.repository;

import com.project.ebossy.view.SalleDisponible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalleDisponibleRepository extends JpaRepository<SalleDisponible, Integer> {
    @Query("select s from SalleDisponible s where s.idEcole = ?1")
    List<SalleDisponible> findByEcole(Integer idEcole);
}
