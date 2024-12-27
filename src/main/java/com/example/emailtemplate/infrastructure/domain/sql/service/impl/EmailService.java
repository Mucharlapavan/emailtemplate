package com.example.emailtemplate.infrastructure.domain.sql.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmailWithTemplate(String to, String subject) throws MessagingException {
        // Create the MIME message
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        // Set the recipient, subject, etc.
        helper.setTo(to);
        helper.setSubject(subject);

        // Template content (this could be dynamic if you want)
        String template = "<html><body><h1>Welcome to our service!</h1><p>Thank you for registering with us.</p></body></html>"; // example template

        // Set the email content to the template
        helper.setText(template, true);

        // Send the email
        mailSender.send(message);
    }
}
