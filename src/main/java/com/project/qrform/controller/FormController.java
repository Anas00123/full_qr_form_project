package com.project.qrform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
public String submit(
        @ModelAttribute("user") @Valid UserForm user,
        BindingResult result,
        @RequestParam("latitude") Double latitude,
        @RequestParam("longitude") Double longitude,
        Model model) {

    if (result.hasErrors()) {
        return "form";
    }

    // Check if user is within allowed area
    if (latitude == null || longitude == null) {
        model.addAttribute("error", "Cannot detect your location.");
        return "form";
    }

    boolean allowed = service.isWithinAllowedArea(latitude, longitude);
    if (!allowed) {
        model.addAttribute("error", "You are outside the allowed area!");
        return "form";
    }

    // Save the user (coordinates are NOT saved)
    service.save(user);
    model.addAttribute("submittedUser", user);
    return "success";
}
}

