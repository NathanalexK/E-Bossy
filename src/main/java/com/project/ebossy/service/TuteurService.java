package com.project.ebossy.service;


import com.project.ebossy.model.Tuteur;
import com.project.ebossy.repository.TuteurRepository;
import com.project.ebossy.util.UtilDate;
import com.project.ebossy.util.Utility;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TuteurService {

    private final TuteurRepository tuteurRepository;

    public TuteurService(TuteurRepository tuteurRepository) {
        this.tuteurRepository = tuteurRepository;
    }

    public List<Tuteur> findAll() {
        return tuteurRepository.findAll();
    }

    public Tuteur findById(int id) {
        Optional<Tuteur> tuteur = tuteurRepository.findById(id);
        if (tuteur.isPresent()) {
            return tuteur.get();
        }
        return null;
    }

    public Tuteur authenticate(String email, String password) {
        return tuteurRepository.findByEmailAndPassword(email, password);
    }

    public Tuteur save(Tuteur tuteur) {
        return tuteurRepository.save(tuteur);
    }

    public Tuteur findByEmail(String email) {
        return tuteurRepository.findByEmail(email);
    }

    public boolean tuteurExists(String email) {
        Tuteur tuteur =  tuteurRepository.findByEmail(email);
        return tuteur != null;
    }

    public Map<String, String> getErreurs (Tuteur tuteur) {
        Map<String, String> erreurs = new HashMap<>();
        if(tuteur.getNom().trim().length() < 3){
            erreurs.put("nom", "Le nom est invalide");
        }
        if(tuteur.getPrenom().trim().length() < 3){
            erreurs.put("prenom", "Le prenom est invalide");
        }
        if(tuteur.getDateNaissance() == null) {
            erreurs.put("dateNaissance", "La dateNaissance est requis");
        }
        else if (UtilDate.calculAge(tuteur.getDateNaissance()) < 21){
            erreurs.put("dateNaissance", "Parent doit être agé d'au moins 21 ans");
        }
        if(!Utility.isValidEmail(tuteur.getEmail())){
            erreurs.put("email", "L' email est invalide");
        }
        else if(tuteurExists(tuteur.getEmail())){
            erreurs.put("email", "L'email " + tuteur.getEmail() + "est déjà utilisé");
        }
        return erreurs;
    }


}
