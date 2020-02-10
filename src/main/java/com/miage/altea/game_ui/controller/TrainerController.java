package com.miage.altea.game_ui.controller;

import com.miage.altea.game_ui.trainers.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TrainerController {

    private TrainerService trainerService;

    @GetMapping("/trainers")
    public ModelAndView trainers(){
        var mav = new ModelAndView();

        mav.addObject("trainers", trainerService.listTrainers());
        mav.setViewName("trainers");

        return mav;
    }

    @GetMapping("/trainers/{name}")
    public ModelAndView trainersById(@PathVariable String name){
        var mav = new ModelAndView();

        mav.addObject("trainer", trainerService.getTrainer(name));
        mav.setViewName("detailTrainer");

        return mav;
    }

    @Autowired
    public void setTrainerService(TrainerService trainerService) {
        this.trainerService = trainerService;
    }
}
