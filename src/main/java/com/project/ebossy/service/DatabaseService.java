package com.project.ebossy.service;

import com.project.ebossy.model.AnneeScolaire;
import com.project.ebossy.model.Ecole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getAnneeScolaireActuelByEcole(Ecole ecole){
        String sql = "select * from annee_scolaire_actuel where id_ecole=?";
        return (int) ((Integer) jdbcTemplate.queryForMap(sql, ecole.getId()).get("id_annee_scolaire"));
    }

    public Long getCapaciteSalleByEcole(Ecole ecole){
        String sql = "select sum(capacite) as capacite from salle where id_ecole=?";
        return (Long) jdbcTemplate.queryForMap(sql, ecole.getId()).get("capacite");
    }

    public Long getNombreSalleByEcole(Ecole ecole){
        String sql = "select count(*) as nombre from salle where id_ecole=?";
        return (Long) jdbcTemplate.queryForMap(sql, ecole.getId()).get("nombre");
    }
}
