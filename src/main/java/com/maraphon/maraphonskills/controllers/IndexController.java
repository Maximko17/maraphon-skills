package com.maraphon.maraphonskills.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Controller
public class IndexController {

    private CountDown countDown = new CountDown();

    @GetMapping({"/", "/index"})
    public String getIndexPage(Model model) {
        model.addAttribute("countDown", countDown.getCountDown());
        return "index";
    }

    @GetMapping("/registerAsRunner")
    public String registerRunner(Model model) {
        model.addAttribute("countDown", countDown.getCountDown());
        return "runner/createOrRegister";
    }

    @GetMapping("/learnMore")
    public String getLearnMorePage(Model model) {
        model.addAttribute("countDown", countDown.getCountDown());
        return "learnmore/main";
    }
}
