package com.project.ebossy.service;

import com.project.ebossy.model.AnneeScolaire;
import com.project.ebossy.model.Ecole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class DatabaseService {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getAnneeScolaireActuelByEcole(Ecole ecole){
        String sql = "select * from annee_scolaire_actuel where id_ecole=?";
        return (int) ((Integer) jdbcTemplate.queryForMap(sql, ecole.getId()).get("id_annee_scolaire"));
    }
}
