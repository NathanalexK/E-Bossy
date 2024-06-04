package com.project.ebossy.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "ecole")
public class Ecole {
    @Id
    @ColumnDefault("nextval('ecole_id_seq'::regclass)")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nom_ecole", length = 50)
    private String nomEcole;

    @Column(name = "localisation", length = 50)
    private String localisation;

    @Column(name = "id_type_ecole", length = 40)
    private int idEcole;
}