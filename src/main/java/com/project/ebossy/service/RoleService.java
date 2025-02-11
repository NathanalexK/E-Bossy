package com.project.ebossy.service;

import com.project.ebossy.exception.UnallowedRoleException;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final HttpSession httpSession;

    public RoleService(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    public void allowedRoles(String... roles) {
        String role = httpSession.getAttribute("role").toString();

        for (String roleName : roles) {
            if (role.equalsIgnoreCase(roleName)) {
                return;
            }
        }

        throw new UnallowedRoleException();
    }

    public boolean canAccess(String... roles) {
        String role = httpSession.getAttribute("role").toString();
        for (String roleName : roles) {
            if (role.equalsIgnoreCase(roleName)) {
                return true;
            }
        }

        return false;
    }
}
