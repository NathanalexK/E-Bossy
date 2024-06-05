package com.project.ebossy.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

/**
 * Mapping for DB view
 */
@Getter
@Setter
@Entity
@Immutable
@Table(name = "v_salles_disponibles")
public class SalleDisponible {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "numero", length = 30)
    private String numero;

    @Column(name = "capacite")
    private Integer capacite;

    @Column(name = "id_ecole")
    private Integer idEcole;

}