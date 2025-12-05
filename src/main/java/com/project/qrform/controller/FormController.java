
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
  public String submit(@ModelAttribute("user") @Valid UserForm user) {
    service.save(user);
    return "success";
  }
}
