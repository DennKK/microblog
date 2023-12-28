package com.boot.microblog.controllers;

import com.boot.microblog.domain.Role;
import com.boot.microblog.domain.UserEntity;
import com.boot.microblog.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    UserRepo userRepo;

    @GetMapping("/register")
    public String registration(Model model) {
        return "registration";
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute UserEntity user, Model model) {
        UserEntity userFromDb = userRepo.findByName(user.getName());
        if (userFromDb != null) {
            model.addAttribute("message", "User already exists!");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        return "redirect:/login";
    }
}