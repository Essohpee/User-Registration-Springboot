package com.essogpee.userRegistration.controller;

import com.essogpee.userRegistration.service.UserService;
import com.essogpee.userRegistration.service.dto.UserRegistrationDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegisterUserController {

    private UserService userService;

    public RegisterUserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegistrationDTO returnNewUserRegistrationDTO() {
        return new UserRegistrationDTO();
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/users")
    public String registerUserAccount( @Valid @ModelAttribute("user") UserRegistrationDTO registerDTO) {
        userService.save(registerDTO);
        return "redirect:/register?exit";
    }
}
