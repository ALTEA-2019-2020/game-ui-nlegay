package com.miage.altea.game_ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @PostMapping("/registerTrainer")
    ModelAndView registerNewTrainer(@Valid @RequestBody String trainerName){
        var mav = new ModelAndView();

        mav.addObject("name", trainerName);
        mav.setViewName("register");

        return mav;
    }
}
