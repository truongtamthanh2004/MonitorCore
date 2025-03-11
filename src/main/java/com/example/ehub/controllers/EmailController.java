package com.example.ehub.controllers;

import com.example.ehub.services.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/emails")
public class EmailController {

    private final EmailService emailService;
    private static final Logger log = LoggerFactory.getLogger(EmailController.class);

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/send-email")
    public void sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String body) {
        log.info("Sending email to {}", to);
        emailService.send(to, subject, body);
    }

    @PostMapping("/send-verification-email")
    public void sendVerificationEmail(@RequestParam String to, @RequestParam String name) {
        try {
            emailService.sendVerificationEmail(to, name);
            log.info("Verification email sent successfully!");
        } catch (Exception e) {
            log.info("Failed to send verification email.");
        }
    }
}
