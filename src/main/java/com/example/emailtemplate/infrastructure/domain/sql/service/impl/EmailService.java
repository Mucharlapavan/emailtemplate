package com.example.emailtemplate.infrastructure.domain.sql.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.util.Map;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailTemplateService emailTemplateService;

    public void sendFestivalEmail(String to, String subject, String templateName, Map<String, Object> variables) throws MessagingException {
        // Create MIME message
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        // Set recipient and subject
        helper.setTo(to);
        helper.setSubject(subject);

        // Generate email content using the template
        String emailContent = emailTemplateService.getTemplateContent(templateName, variables);

        // Set the email content
        helper.setText(emailContent, true);

        // Send the email
        mailSender.send(message);
    }
}
