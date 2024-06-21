package com.project.ebossy.service;


import com.project.ebossy.util.Role;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Service
public class LayoutService {

    private final HttpSession httpSession;

    public LayoutService(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    public ModelAndView getLayout() {
        String role = httpSession.getAttribute("role").toString();
//        boolean isAllowed = false;
//
//        for (String allowedRole : allowedRoles) {
//            if (role.equalsIgnoreCase(allowedRole)) {
//                isAllowed = true;
//                break;
//            }
//        }
//
//        if (!isAllowed) {
//            return new ModelAndView("redirect:/");
//        }

        return switch (role) {
            case Role.ADMIN -> new ModelAndView("admin/layout");
            case Role.DIRECTEUR -> new ModelAndView("direction/layout");
            case Role.PROFESSEUR -> new ModelAndView("professeur/layout");
            case Role.SECRETAIRE -> new ModelAndView("secretaire/layout");
            case Role.TUTEUR -> new ModelAndView("tuteur/layout");
            case Role.ELEVE -> new ModelAndView("eleve/layout");
            default -> throw new RuntimeException("Undefined role: " + role);
        };
    }
}
