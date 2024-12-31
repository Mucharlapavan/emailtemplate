package com.example.emailtemplate.infrastructure.domain.sql.service.impl;

import com.example.emailtemplate.core.utils.EmailValidator;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.emailtemplate.infrastructure.domain.sql.model.User;
import com.example.emailtemplate.infrastructure.domain.sql.repository.UserRepository;

import java.time.LocalDate;
import java.time.MonthDay;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailValidator emailValidator;

    public User registerUser(User user) {
        // Validate email format before saving
        if (!EmailValidator.isValidEmail(user.getEmail())) {
            throw new IllegalArgumentException("Invalid email format");
        }

        // Save the user to the database
        User savedUser = userRepository.save(user);

        // Determine the current festival and send an email
        try {
            String currentFestival = determineCurrentFestival();
            String templateName = getTemplateForFestival(currentFestival);

            // Prepare dynamic variables for the email template
            Map<String, Object> variables = new HashMap<>();
            variables.put("name", savedUser.getName());
            variables.put("festival", currentFestival);

            // Send the email with the selected template
            emailService.sendFestivalEmail(savedUser.getEmail(), "Happy " + currentFestival + "!", templateName, variables);

            System.out.println("Email sent to " + savedUser.getEmail());
        } catch (MessagingException e) {
            e.printStackTrace();
            // Handle email-sending failure (e.g., log or rethrow exception)
        }

        return savedUser;
    }

    private String determineCurrentFestival() {
        // Example: Use calendar-based logic to determine the current festival
        LocalDate today = LocalDate.now();
        MonthDay currentDay = MonthDay.from(today);

        if (currentDay.equals(MonthDay.of(12, 31))) {
            return "Advance New Year";
        }else if (currentDay.equals(MonthDay.of(1, 1))) {
            return "New Year";
        } else if (currentDay.equals(MonthDay.of(12, 25))) {
            return "Christmas";
        } else if (currentDay.equals(MonthDay.of(10, 24))) { // Example date for Diwali
            return "Diwali";
        }

        return "Default"; // Fallback in case no festival matches
    }

    private String getTemplateForFestival(String festival) {
        // Map festival names to template file names
        switch (festival) {
            case "Advance New Year":
                return "advance_New_Year_email"; //Corresponds to Advance_new_year.html
            case "New Year":
                return "new_year_email"; // Corresponds to new_year_email.html
            case "Christmas":
                return "christmas_email"; // Corresponds to christmas_email.html
            case "Diwali":
                return "diwali_email"; // Corresponds to diwali_email.html
            default:
                return "default_email"; // A default fallback template
        }
    }
}
