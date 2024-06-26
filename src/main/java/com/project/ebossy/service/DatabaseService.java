package com.project.ebossy.service;

import com.project.ebossy.model.AnneeScolaire;
import com.project.ebossy.model.Ecole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Long getCapaciteSalleByEcole(AnneeScolaire anneeScolaire){
        String sql = "select sum(capacite) as capacite from salle where id_annee_scolaire=?";
        return (Long) jdbcTemplate.queryForMap(sql, anneeScolaire.getId()).get("capacite");
    }

    public Long getNombreSalleByEcole(AnneeScolaire anneeScolaire){
        String sql = "select count(*) as nombre from salle where id_annee_scolaire=?";
        return (Long) jdbcTemplate.queryForMap(sql, anneeScolaire.getId()).get("nombre");
    }

    public boolean TuteurEmailExist(String email){
        String sql = "select * from tuteur where email=?";
        return !jdbcTemplate.queryForMap(sql, email).isEmpty();
    }

    public Map<Integer, Map<String, Object>> getEleveRangAnneeScolaire(int idEleve) {
        String sql = "select * from v_rang_par_classe_et_periode where id_eleve=?";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, idEleve);
        Map<Integer, Map<String, Object>> map = new HashMap<>();

        list.forEach(element -> map.put(((Integer) element.get("id_periode_note")), element));

        return map;
    }

    public Map<Integer, Map<String, Object>> getStatitstiqueAnnuelClasse(int idClasse) {
        String sql = "select * from v_stat_classe_par_classe_et_periode where id_classe=?";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, idClasse);
        Map<Integer, Map<String, Object>> map = new HashMap<>();

        list.forEach(element -> map.put(((Integer) element.get("id_periode_note")), element));

        return map;
    }

    public Double getMoyenneAnneeScolaire(AnneeScolaire anneeScolaire){
        String sql = "select avg from v_moyenne_generale_by_annee_scolaire where id_annee_scolaire=?";
        Map<String, Object> map = null;

        try {
            map = jdbcTemplate.queryForMap(sql, anneeScolaire.getId());
        } catch (EmptyResultDataAccessException e) {
            return 0.0;
        }
        return ((BigDecimal) map.get("avg")).doubleValue();
    }

    public Map<Integer, Double> getDashboardMoyenneParPeriode(AnneeScolaire anneeScolaire) {
        String sql = "select * from v_dashboard_moyenne_par_periode where id_annee_scolaire=?";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, anneeScolaire.getId());
        Map<Integer, Double> map = new HashMap<>();

        list.forEach(element -> map.put((Integer) element.get("id_periode_note"), ((BigDecimal) element.get("avg")).doubleValue()));

        return map;
    }

    public Integer getNombresElevesParAnneeScolaire(AnneeScolaire anneeScolaire){
        String sql = "select * from v_nombres_eleves_par_annee_scolaire where id_annee_scolaire=?";
        Map<String, Object> map = null;
        try{
            map = jdbcTemplate.queryForMap(sql, anneeScolaire.getId());
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
        return ((Long) map.get("count")).intValue();
    }

    public Map<Integer, Integer> getSexeParAnneeScolaire(AnneeScolaire anneeScolaire) {
        String sql = "select * from v_sexe_par_annee_scolaire where id_annee_scolaire=?";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, anneeScolaire.getId());
        Map<Integer, Integer> map = new HashMap<>();

        list.forEach(element -> map.put(((Integer) element.get("id_sexe")), ((Long) element.get("nombre")).intValue()));

        return map;
    }

    public Map<Integer, Double> getMoyenneMatiereParAnneeScolaire(AnneeScolaire anneeScolaire){
        String sql = "select * from v_moyenne_par_matiere_et_annee_scolaire where id_annee_scolaire=?";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, anneeScolaire.getId());
        Map<Integer, Double> map = new HashMap<>();

        list.forEach(element -> map.put(((Integer) element.get("id_matiere")), ((BigDecimal) element.get("avg")).doubleValue()));

        return map;
    }
}
