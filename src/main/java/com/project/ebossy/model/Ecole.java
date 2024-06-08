package com.project.ebossy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Fetch;

@Getter
@Setter
@Entity
@Table(name = "ecole")
public class Ecole {
    @Id
    @ColumnDefault("nextval('ecole_id_seq'::regclass)")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nom_ecole", length = 50)
    private String nomEcole;

    @Column(name = "localisation", length = 50)
    private String localisation;

    @Column(name = "id_type_ecole", length = 40)
    private int idTypeEcole;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_annee_scolaire")
    private AnneeScolaire anneeScolaire;

}