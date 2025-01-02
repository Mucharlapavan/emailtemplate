package com.example.emailtemplate.infrastructure.domain.sql.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Map;

@Service
public class EmailTemplateService {

    @Autowired
    private SpringTemplateEngine templateEngine;

    public String getTemplateContent(String templateName, Map<String, Object> variables) {
        Context context = new Context();
        context.setVariables(variables);

        // Process the template and return the email content
        return templateEngine.process(templateName, context);
    }
}
