package com.alquiler.reservas.internationalization;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class SpringConfiguration {

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource rbms = new ResourceBundleMessageSource();
        rbms.setBasename("i18n/message");
        return rbms;
    }
    
}