package com.miage.altea.game_ui.controller;

import com.miage.altea.game_ui.trainers.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class ProfileController {

    private TrainerService trainerService;

    @GetMapping("/profile")
    public ModelAndView profile(Principal principal) {
        var mav = new ModelAndView();
        mav.addObject("trainer", trainerService.getTrainer( principal.getName() ));
        mav.setViewName("profile");

        return mav;
    }

    @Autowired
    public void setTrainerService(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

}
