package com.accenture.itfactory.configurations;

import com.accenture.itfactory.controllers.QuestionController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class ControllerConfig extends ResourceConfig {
    public ControllerConfig() {
        register(QuestionController.class);
    }
}
