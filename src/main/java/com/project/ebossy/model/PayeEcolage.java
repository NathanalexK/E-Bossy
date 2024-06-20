package com.project.ebossy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "paye_ecolage")
public class PayeEcolage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ecole")
    private Ecole idEcole;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_eleve")
    private Eleve idEleve;

    @Column(name = "date_payement")
    private LocalDate datePayement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_periode_ecolage")
    private PeriodeEcolage idPeriodeEcolage;

}