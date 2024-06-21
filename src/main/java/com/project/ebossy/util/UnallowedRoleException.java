package com.project.ebossy.util;

public class UnallowedRoleException extends RuntimeException {
    public UnallowedRoleException() {
        super("L'utilisateur n'est pas autorisé");
    }
}
