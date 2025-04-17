package com.marsc.marsc_web.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    // Show custom login page (mapped to /login in SecurityConfig)
    @GetMapping("/login")
    public String loginPage(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            @RequestParam(value = "expired", required = false) String expired,
            Model model) {

        if (error != null) {
            model.addAttribute("error", "Invalid username or password!");
        }
        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }
        if (expired != null) {
            model.addAttribute("message", "Session expired. Please login again.");
        }

        return "login"; // Thymeleaf template (src/main/resources/templates/login.html)
    }
}