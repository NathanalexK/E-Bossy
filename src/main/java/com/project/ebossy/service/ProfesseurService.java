package com.project.ebossy.service;


import com.project.ebossy.model.Ecole;
import com.project.ebossy.model.Professeur;
import com.project.ebossy.model.Tuteur;
import com.project.ebossy.repository.ProfesseurRepository;
import com.project.ebossy.util.UtilDate;
import com.project.ebossy.util.Utility;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProfesseurService {

    private final ProfesseurRepository professeurRepository;

    public ProfesseurService(ProfesseurRepository professeurRepository) {
        this.professeurRepository = professeurRepository;
    }

    public List<Professeur> findAllByEcole(Ecole ecole) {
        return professeurRepository.findAllByEcole(ecole);
    }

    public Professeur save(Professeur professeur) {
        return professeurRepository.save(professeur);
    }

    public Professeur findByIdentifiant(String identifiant) {
        return professeurRepository.findByIdentifiant(identifiant);
    }

    public Map<String, String> getErreurs (Professeur professeur) {
        Map<String, String> erreurs = new HashMap<>();
        if(professeur.getNom().trim().length() < 3){
            erreurs.put("nom", "Le nom doit contenir au moins 3 caractères");
        }
        if(professeur.getDateNaissance() == null) {
            erreurs.put("dateNaissance", "La dateNaissance est requis");
        }
        else if (UtilDate.calculAge(professeur.getDateNaissance()) < 21){
            erreurs.put("dateNaissance", "Professeur doit être agé d'au moins 18 ans");
        }
        if(!Utility.isValidEmail(professeur.getEmail())){
            erreurs.put("email", "L' email est invalide");
        }
        if(findByIdentifiant(professeur.getIdentifiant()) != null) {
            erreurs.put("identifiant", "L'identifiant existe déjà");
        }
        if(professeur.getMdp().trim().length() < 4) {
            erreurs.put("mdp", "Le mot de passe doit contenir au moins 4 caractères");
        }
        return erreurs;
    }
}
