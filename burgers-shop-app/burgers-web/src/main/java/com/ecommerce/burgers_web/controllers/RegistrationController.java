package com.ecommerce.burgers_web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.burgers_models.dtos.RegistrationForm;
import com.ecommerce.burgers_models.models.User;
import com.ecommerce.burgers_repository.repository.UserRepository;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form) {

        log.info("Processing Registration : " + form);
        User user = form.toUser(passwordEncoder);
        log.info("Processing Registration : " + user);
        userRepo.save(form.toUser(passwordEncoder));
        return "redirect:/login";
    }

}
