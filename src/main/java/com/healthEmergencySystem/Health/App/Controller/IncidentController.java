package com.healthEmergencySystem.Health.App.Controller;

import com.healthEmergencySystem.Health.App.Domain.Incident;
import com.healthEmergencySystem.Health.App.Domain.User;
import com.healthEmergencySystem.Health.App.Repositories.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/incident/")
public class IncidentController {

    private final IncidentRepository repo;

    @Autowired
    public IncidentController(IncidentRepository repo) {
        this.repo = repo;
    }

    @GetMapping("add")
    public String showRegisterForm(Incident incident) {
        return "./dashboard/add-incident";
    }

    @GetMapping("list")
    public String showUpdateForm(Model model) {
        model.addAttribute("incidents", repo.findAll());
        return "./dashboard/incidents";
    }

    @PostMapping("register")
    public String addIncident(@Valid Incident incident, BindingResult result, Model model, RedirectAttributes redirectAttributes, Principal principal, @AuthenticationPrincipal User user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        try {
            if (result.hasErrors()) {
                return "add-incident";
            }

incident.setRegisteredBy(principal.getName());

            repo.save(incident);
            redirectAttributes.addFlashAttribute("message", "Incident saved successfully!");

        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }

        return "redirect:list";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Incident incident = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Incident Id:" + id));
        model.addAttribute("incident", incident);
        return "./dashboard/edit-incident";
    }

    @PostMapping("update/{id}")
    public String updateIncident(@PathVariable("id") long id, @Valid Incident incident, BindingResult result,
                                Model model, RedirectAttributes redirectAttributes, Principal principal) {

        try {
            if (result.hasErrors()) {
                incident.setId(id);
                return "./dashboard/edit-incident";
            }
incident.setRegisteredBy(principal.getName());
            repo.save(incident);
            model.addAttribute("incidents", repo.findAll());
            redirectAttributes.addFlashAttribute("message", "Incident updated successfully!");

        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }

        return "redirect:../list";
    }

    @GetMapping("delete/{id}")
    public String deleteIncident(@PathVariable("id") long id, Model model, RedirectAttributes redirectAttributes) {

        try {
            Incident incident = repo.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Incident Id:" + id));
            repo.delete(incident);
            model.addAttribute("incidents", repo.findAll());
            redirectAttributes.addFlashAttribute("message", "Incident deleted successfully!");

        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }

        return "redirect:../list";
    }

}
