package com.project.ebossy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "convocation")
public class Convocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ecole")
    private Ecole idEcole;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_eleve_annee_scolaire")
    private EleveAnneeScolaire idEleveAnneeScolaire;

    @Column(name = "date_convocation")
    private LocalDate dateConvocation;

    @Column(name = "motif", length = 200)
    private String motif;

}