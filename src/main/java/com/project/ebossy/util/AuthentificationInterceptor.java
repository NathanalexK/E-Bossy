package com.project.ebossy.util;

import com.project.ebossy.model.Ecole;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthentificationInterceptor implements HandlerInterceptor {
    private final HttpSession httpSession;

    public AuthentificationInterceptor(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if(request.getSession().getAttribute("utilisateur") == null){
            response.sendRedirect("/index");
            return false;
        }
        return true;
    }
}
