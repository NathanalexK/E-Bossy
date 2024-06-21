package com.project.ebossy.model;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Fetch;

@Getter
@Setter
@Entity
@Transactional
@Table(name = "ecole")
public class Ecole {
    @Id
    @ColumnDefault("nextval('ecole_id_seq'::regclass)")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nom_ecole", length = 50)
    private String nomEcole;

    @Column(name = "localisation", length = 50)
    private String localisation;

    @Column(name = "id_type_ecole", length = 40)
    private Integer idTypeEcole;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_annee_scolaire")
    private AnneeScolaire anneeScolaire;

}