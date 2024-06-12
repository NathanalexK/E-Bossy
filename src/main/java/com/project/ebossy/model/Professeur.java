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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "professeur_seq")
    @SequenceGenerator(name = "professeur_seq", sequenceName = "professeur_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nom", length = 50)
    private String nom;

    @Column(name = "prenom", length = 50)
    private String prenom;

    @Column(name = "date_naissance")
    private LocalDate dateNaissance;

    @Column(name = "adresse", length = 50)
    private String adresse;

    @Column(name = "conctact", nullable = false, length = 50)
    private String contact;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "mdp", nullable = false, length = 50)
    private String mdp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ecole")
    private Ecole idEcole;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sexe")
    private Sexe idSexe;

}