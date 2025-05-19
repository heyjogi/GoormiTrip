package com.goormitrip.goormitrip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model, Principal principal) {
        String email = principal.getName();
        model.addAttribute("email", email);
        return "main";
    }


}
