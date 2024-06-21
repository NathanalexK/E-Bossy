package com.project.ebossy.util;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UnallowedRoleException.class)
    public ModelAndView handleUnallowedRoleException(UnallowedRoleException ignored) {
        return new ModelAndView("redirect:/erreur");
    }
}
