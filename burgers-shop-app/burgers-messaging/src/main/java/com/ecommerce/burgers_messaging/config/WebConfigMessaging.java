package com.ecommerce.burgers_messaging.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Profile({"jms-template", "rabbitmq-template"})
@Configuration
public class WebConfigMessaging implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/orders/receive");
    }

}
