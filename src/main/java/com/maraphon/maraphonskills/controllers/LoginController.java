package com.maraphon.maraphonskills.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class LoginController {


    @GetMapping("/login")
    public String login(Model model) {

        model.addAttribute("countDown", new CountDown().getCountDown());
        return "login/login";
    }

}
