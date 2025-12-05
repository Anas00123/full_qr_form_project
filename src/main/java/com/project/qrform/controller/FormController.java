
package com.project.qrform.controller;

import com.project.qrform.entity.UserForm;
import com.project.qrform.service.UserFormService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class FormController {

  private final UserFormService service;
  public FormController(UserFormService service){ this.service=service; }

  @GetMapping("/form")
  public String form(Model model){
    model.addAttribute("user", new UserForm());
    return "form";
  }

  @PostMapping("/form")
  public String submit(@ModelAttribute("user") @Valid UserForm user,
                       BindingResult result){
    if(result.hasErrors()) return "form";
    service.save(user);
    return "success";
  }
}
