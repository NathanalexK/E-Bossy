package com.project.ebossy.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "salle")
public class Salle {
    @Id
    @ColumnDefault("nextval('salle_id_seq'::regclass)")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "numero", length = 30)
    private String numero;

    @Column(name = "capacite")
    private Integer capacite;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ecole")
    private Ecole idEcole;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_annee_scolaire")
    private AnneeScolaire anneeScolaire;
}

