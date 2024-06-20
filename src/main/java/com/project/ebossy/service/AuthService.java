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
                httpSession.setAttribute("ecole", ((Dirigeant) utilisateur).getIdEcole());
            }
            case Role.PROFESSEUR -> {
                utilisateur = professeurService.authenticate(username, password);
                httpSession.setAttribute("ecole", ((Professeur) utilisateur).getIdEcole());
            }
            default -> {
//                return null;
            }
        }
        httpSession.setAttribute("utilisateur", utilisateur);
        return utilisateur;
    }
}
