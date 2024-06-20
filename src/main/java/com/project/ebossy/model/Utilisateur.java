package com.project.ebossy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
public class Utilisateur {
    Ecole ecole;
    AnneeScolaire anneeScolaire;


//    private Long id;
//
//    private String mdp;
//
//
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_type_user")
//    private TypeUser idTypeUser;

}