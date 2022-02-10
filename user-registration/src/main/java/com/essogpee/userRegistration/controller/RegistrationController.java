package com.essogpee.userRegistration.controller;

import com.essogpee.userRegistration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistrationController {

    @Autowired
    private UserService service;

    @GetMapping("/login")
    public String startSession() {
        return "login";
    }

    @GetMapping("/")
    public String startPage(Model model) {
        model.addAttribute("users", service.listUsers());
        return "index";
    }
}
