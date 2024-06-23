package com.project.ebossy.service;

import com.project.ebossy.model.Dirigeant;
import com.project.ebossy.model.Professeur;
import com.project.ebossy.util.Role;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final DirigeantService dirigeantService;
    private final ProfesseurService professeurService;
    private final HttpSession httpSession;

    public AuthService(DirigeantService dirigeantService, ProfesseurService professeurService, HttpSession httpSession) {
        this.dirigeantService = dirigeantService;
        this.professeurService = professeurService;
        this.httpSession = httpSession;
    }

    public Object authenticate(String username, String password, String role) {
        Object utilisateur = null;
        switch (role) {
            case Role.DIRECTEUR -> {
                utilisateur =  dirigeantService.authenticate(username, password);
                if(utilisateur != null) {
                    httpSession.setAttribute("ecole", ((Dirigeant) utilisateur).getIdEcole());
                    httpSession.setAttribute("anneeScolaire", ((Dirigeant) utilisateur).getIdEcole().getAnneeScolaire());
                }
            }
            case Role.PROFESSEUR -> {
                utilisateur = professeurService.authenticate(username, password);
                if(utilisateur != null) {
                    httpSession.setAttribute("ecole", ((Professeur) utilisateur).getIdEcole());
                    httpSession.setAttribute("anneeScolaire", ((Professeur) utilisateur).getIdEcole().getAnneeScolaire());
                }
            }
            default -> {
//                return null;
            }
        }
        httpSession.setAttribute("utilisateur", utilisateur);
        return utilisateur;
    }
}
