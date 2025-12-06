package com.project.qrform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.qrform.entity.UserForm;
import com.project.qrform.service.UserFormService;

import jakarta.validation.Valid;

@Controller
public class FormController {

    private final UserFormService service;

    public FormController(UserFormService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String form(Model model) {
        model.addAttribute("user", new UserForm());
        return "form";
    }

    @PostMapping("/form")
    public String submit(@ModelAttribute("user") @Valid UserForm user,
                         org.springframework.validation.BindingResult result,
                         Model model) {

        // Check form validation errors
        if (result.hasErrors()) {
            return "form";
        }

        // Check if latitude and longitude are present
        if (user.getLatitude() == null || user.getLongitude() == null) {
            model.addAttribute("error", "Cannot detect your location. Please allow location access.");
            return "form";
        }

        // Check if user is within allowed area
        boolean allowed = service.isWithinAllowedArea(user.getLatitude(), user.getLongitude());
        if (!allowed) {
            model.addAttribute("error", "You are outside the allowed area!");
            return "form";
        }

        // Save the user
        service.save(user);

        // Optional: pass submitted data to success page
        model.addAttribute("submittedUser", user);
        return "success";
    }
}
