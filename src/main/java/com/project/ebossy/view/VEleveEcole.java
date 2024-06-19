package com.project.ebossy.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.time.LocalDate;

/**
 * Mapping for DB view
 */
@Getter
@Setter
@Entity
@Immutable
@Table(name = "v_eleve_ecole")
public class VEleveEcole {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "nom", length = 50)
    private String nom;

    @Column(name = "prenom", length = 50)
    private String prenom;

    @Column(name = "id_sexe")
    private Integer idSexe;

    @Column(name = "date_naissance")
    private LocalDate dateNaissance;

    @Column(name = "etablissement_origine", length = 40)
    private String etablissementOrigine;

    @Column(name = "mdp", length = 50)
    private String mdp;

    @Column(name = "id_classe")
    private Integer idClasse;

    @Column(name = "id_tuteur")
    private Integer idTuteur;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "id_ecole")
    private Integer idEcole;

}