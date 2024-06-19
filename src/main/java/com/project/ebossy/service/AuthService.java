package com.project.ebossy.service;

import com.project.ebossy.util.Role;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final DirigeantService dirigeantService;
    private final ProfesseurService professeurService;

    public AuthService(DirigeantService dirigeantService, ProfesseurService professeurService) {
        this.dirigeantService = dirigeantService;
        this.professeurService = professeurService;
    }

    public Object authenticate(String username, String password, String role) {
        switch (role) {
            case Role.DIRECTEUR -> {
                return dirigeantService.authenticate(username, password);
            }
            case Role.PROFESSEUR -> {
                return professeurService.authenticate(username, password);
            }
            default -> {
                return null;
            }
        }
    }
}
