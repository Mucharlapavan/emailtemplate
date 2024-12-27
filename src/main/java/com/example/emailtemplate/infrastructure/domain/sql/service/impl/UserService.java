package com.example.emailtemplate.infrastructure.domain.sql.service.impl;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.emailtemplate.infrastructure.domain.sql.model.User;
import com.example.emailtemplate.infrastructure.domain.sql.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        // Save the user to the database
        User savedUser = userRepository.save(user);

        // After saving the user, send an email to the user
        try {
            emailService.sendEmailWithTemplate(savedUser.getEmail(), "Welcome to Our Service");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return savedUser;
    }
}
