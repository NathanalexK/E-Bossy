package com.project.ebossy.exception;

public class UnallowedRoleException extends RuntimeException {
    public UnallowedRoleException() {
        super("L'utilisateur n'est pas autorisé");
    }
}
