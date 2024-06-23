package com.project.ebossy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "note", uniqueConstraints = @UniqueConstraint(
        columnNames = {"id_eleve", "id_periode_note", "id_matiere_prof"}
))
public class Note {
    @Id
    @ColumnDefault("nextval('note_id_seq'::regclass)")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_matiere_prof")
    private MatiereProf matiereProf;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_eleve")
    private Eleve idEleve;

    @Column(name = "note")
    private Double note;

    @Column(name = "appreciation")
    private String appreciation;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_periode_note")
    private PeriodeNote idPeriodeNote;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_matiere_niveau")
//    private MatiereNiveau matiereNiveau;
}