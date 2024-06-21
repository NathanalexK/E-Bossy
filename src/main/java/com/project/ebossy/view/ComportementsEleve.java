package com.project.ebossy.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "v_comportements_eleve")
public class ComportementsEleve {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "id_eleve")
    private Integer idEleve;

    @Column(name = "id_classe")
    private Integer idClasse;

    @Column(name = "id_comportements")
    private Integer idComportements; 
}
