package com.project.ebossy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "absence")
public class Absence {
    @Id
    @ColumnDefault("nextval('absence_id_seq'::regclass)")
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_classe")
    private Classe idClasse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_eleve")
    private Eleve idEleve;

    @Column(name = "date_absence")
    private LocalDate dateAbsence;

}