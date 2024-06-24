package com.project.ebossy.service;


import com.project.ebossy.model.AnneeScolaire;
import com.project.ebossy.model.Ecole;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    @Autowired
    private HttpSession session;

    public AnneeScolaire getAnneeScolaire() {
        return (AnneeScolaire) session.getAttribute("anneeScolaire");
    }

    public Ecole getEcole() {
        return (Ecole) session.getAttribute("ecole");
    }

    public boolean isLogged() {
        return session.getAttribute("utilisateur") != null;
    }
}
