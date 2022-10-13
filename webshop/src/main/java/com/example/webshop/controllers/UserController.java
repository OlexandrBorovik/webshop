package com.example.webshop.controllers;

import com.example.webshop.models.User;
import com.example.webshop.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";

    }

    @PostMapping("/registration")
    public String newUser(User user, Model model) {
        if (!userService.createUser(user)) {
            model.addAttribute("ERROR", "User exist.");
            return "/registration";
        }
        return "redirect:/login";

    }

    @GetMapping("/user/{user}")
    public String userProfile(@PathVariable("user") User user, Model model) {
model.addAttribute("user", user);
model.addAttribute("products", user.getProducts());
        return "user-profile";
    }
}
