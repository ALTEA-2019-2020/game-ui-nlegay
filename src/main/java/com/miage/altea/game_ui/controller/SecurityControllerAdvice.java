package com.miage.altea.game_ui.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class SecurityControllerAdvice {

    @ModelAttribute("user")
    Object principal(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getPrincipal();
    }

}
