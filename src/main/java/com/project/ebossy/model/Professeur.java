package com.project.ebossy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "professeur")
public class Professeur {
    @Id
    @ColumnDefault("nextval('professeur_id_seq'::regclass)")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nom", length = 50)
    private String nom;

    @Column(name = "prenom", length = 50)
    private String prenom;

    @Column(name = "date_naissance")
    private LocalDate dateNaissance;

    @Column(name = "debut_carriere")
    private LocalDate debutCarriere;

    @Column(name = "adresse", length = 50)
    private String adresse;

    @Column(name = "conctact", nullable = false, length = 50)
    private String conctact;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "identifiant", length = 100)
    private String identifiant;

    @Column(name = "mdp", nullable = false, length = 50)
    private String mdp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ecole")
    private Ecole idEcole;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sexe")
    private Sexe idSexe;

}