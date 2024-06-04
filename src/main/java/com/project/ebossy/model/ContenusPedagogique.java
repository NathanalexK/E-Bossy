package com.project.ebossy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "contenus_pedagogique")
public class ContenusPedagogique {
    @Id
    @ColumnDefault("nextval('contenus_pedagogique_id_seq'::regclass)")
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_classe")
    private Classe idClasse;

    @Column(name = "url_fichier", length = 100)
    private String urlFichier;

    @Column(name = "date_contenus")
    private LocalDate dateContenus;

}