package com.project.ebossy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "eleve_annee_scolaire")
public class EleveAnneeScolaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('eleve_annee_scolaire_id_seq'::regclass)")
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_annee_scolaire")
    private AnneeScolaire idAnneeScolaire;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_eleve")
    private Eleve idEleve;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_niveau")
    private Niveau idNiveau;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_classe")
    private Classe idClasse;

}