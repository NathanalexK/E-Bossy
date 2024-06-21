package com.project.ebossy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "prof_matiere")
public class MatiereProf {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prof_matiere_id_gen")
    @SequenceGenerator(name = "prof_matiere_id_gen", sequenceName = "prof_matiere_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_classe")
    private Classe idClasse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_matiere")
    private Matiere idMatiere;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prof")
    private Professeur idProf;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_annee_scolaire")
    private AnneeScolaire idAnneeScolaire;


}