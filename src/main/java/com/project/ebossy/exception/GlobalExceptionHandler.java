package com.project.ebossy.exception;

import com.project.ebossy.service.LayoutService;
import com.project.ebossy.service.SessionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.AuthenticationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final LayoutService layoutService;
    private final SessionService sessionService;
    private final HttpSession httpSession;

    public GlobalExceptionHandler(LayoutService layoutService, SessionService sessionService, HttpSession httpSession) {
        this.layoutService = layoutService;
        this.sessionService = sessionService;
        this.httpSession = httpSession;
    }

    @ExceptionHandler(UnallowedRoleException.class)
    public ModelAndView handleUnallowedRoleException(UnallowedRoleException ignored) {
        return new ModelAndView("redirect:/erreur");
    }

    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFoundException(NotFoundException e) {
        ModelAndView modelAndView = layoutService.getLayout();
        modelAndView.addObject("page", "erreur/not-found");
        modelAndView.addObject("message", e.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(AuthException.class)
    public ModelAndView handleAuthException(AuthException e) {
        System.out.println("Here");
        ModelAndView modelAndView = new ModelAndView("login/connexion");
        modelAndView.addObject("role", httpSession.getAttribute("role"));
        modelAndView.addObject("message", e.getMessage());
        return modelAndView;
    }
}
