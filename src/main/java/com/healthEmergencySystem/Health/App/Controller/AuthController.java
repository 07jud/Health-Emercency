package com.healthEmergencySystem.Health.App.Controller;

import com.healthEmergencySystem.Health.App.Repositories.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AuthController {
    @Autowired
    IncidentRepository repo;

    @RequestMapping("/Dashboard")
    public String showDashboard(Model model){
        model.addAttribute("incidents", repo.findAll());
        return"./Dashboard/index";

    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
}
