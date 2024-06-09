package com.project.ebossy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "eleve")
public class Eleve {
    @Id
    @ColumnDefault("nextval('eleve_id_seq'::regclass)")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nom", length = 50)
    private String nom;

    @Column(name = "prenom", length = 50)
    private String prenom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sexe")
    private Sexe idSexe;

    @Column(name = "date_naissance")
    private LocalDate dateNaissance;

    @Column(name = "etablissement_origine", length = 40)
    private String etablissementOrigine;

    @Column(name = "mdp", nullable = false, length = 50)
    private String mdp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_classe")
    private Classe idClasse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tuteur")
    private Tuteur idTuteur;

    @Column(name = "identifiant")
    private String identifiant;

}