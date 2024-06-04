package com.project.ebossy.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "annee_scolaire")
public class AnneeScolaire {
    @Id
    @ColumnDefault("nextval('annee_scolaire_id_seq'::regclass)")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "annee_debut")
    private Integer anneeDebut;

    @Column(name = "anne_fin")
    private Integer anneFin;

}