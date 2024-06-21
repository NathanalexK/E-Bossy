package com.project.ebossy.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.time.LocalDate;

/**
 * Mapping for DB view
 */
@Getter
@Setter
@Entity
@Immutable
@Table(name = "v_periode_ecolage_for_date")
public class VPeriodeEcolageForDate {
    @Id
    @Column(name = "_id")
    private Integer id;

    @Column(name = "date_debut")
    private LocalDate dateDebut;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    @Column(name = "id_annee_scolaire")
    private Integer idAnneeScolaire;

    @Column(name = "id_ecole")
    private Integer idEcole;

}