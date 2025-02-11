package com.project.ebossy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "matiere")
public class Matiere {
    @Id
    @ColumnDefault("nextval('matiere_id_seq'::regclass)")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nom_matiere", length = 50)
    private String nomMatiere;

//    @Column(name = "coefficient", nullable = false)
//    private Integer coefficient;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_niveau")
//    private Niveau idNiveau;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ecole")
    private Ecole idEcole;


    @OneToMany(mappedBy = "idMatiere", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<MatiereNiveau> matiereNiveaux;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_prof")
//    private Professeur idProf;

}