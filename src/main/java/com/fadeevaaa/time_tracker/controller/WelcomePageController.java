package com.fadeevaaa.time_tracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomePageController {

    @GetMapping("/main-menu")
    public String openMainMenu() {
        return "main_menu";
    }

}