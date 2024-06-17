package com.project.ebossy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "note")
public class Note {
    @Id
    @ColumnDefault("nextval('note_id_seq'::regclass)")
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ecole")
    private Ecole idEcole;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_classe")
    private Classe idClasse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_matiere")
    private Matiere idMatiere;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_professeur")
    private Professeur idProfesseur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_eleve")
    private Eleve idEleve;

    @Column(name = "note", nullable = false, precision = 6, scale = 3)
    private BigDecimal note;

    @Column(name = "appreciation")
    private String appreciation;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_periode_note")
    private PeriodeNote idPeriodeNote;

}