package com.project.ebossy.repository;

import com.project.ebossy.model.Salle;
import com.project.ebossy.view.SalleDisponible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


@Repository
public interface SalleRepository extends JpaRepository<Salle, Integer> {
    @Query("select s from Salle s where s.idEcole.id = ?1")
    List<Salle> findByIdEcole_IdEcole(int idEcole);

    @Query("select s from Salle s where s.idEcole.id = ?1")
    List<Salle> findAllByEcole(int idEcole);

    @Query("select s from Salle s where s.id not in ?1")
    List<Salle> getSalleDisponible(Collection<Integer> ids);

//    @Query("select s from v_salles_disponibles s where s.idEcole.idEcole = ?1")
//    List<Salle> getSalleNonDisponible(int idEcole);
}

