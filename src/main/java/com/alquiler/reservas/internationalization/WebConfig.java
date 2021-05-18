package com.alquiler.reservas.internationalization;


import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;

import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
//import org.springframework.web.reactive.config.EnableWebFlux;
//import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
//import org.springframework.web.reactive.config.ViewResolverRegistry;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.spring5.SpringWebFluxTemplateEngine;
import org.thymeleaf.spring5.ISpringWebFluxTemplateEngine;
import org.thymeleaf.spring5.view.reactive.ThymeleafReactiveViewResolver;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class WebConfig implements ApplicationContextAware{

  private ApplicationContext context;

  @Override
  public void setApplicationContext(ApplicationContext context) {
    this.context = context;
  }
/* 
  @Bean
  public MessageSource messageSource() {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
   // messageSource.setBasenames("C:/Users/enrique/git/base2/src/main/resourcesi18n/messages/");
    messageSource.setBasenames("C:\\Users\\enrique\\git\\base2\\src\\main\\resources\\i18n\\messages");
   // messageSource.setBasename("C:\\Users\\enrique\\git\\base2\\src\\main\\resources\\i18n\\messages");
    messageSource.setDefaultEncoding("UTF-8");
    return messageSource;
  }

  
  @Bean
  public ITemplateResolver thymeleafTemplateResolver() {
    final SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
    resolver.setApplicationContext(this.context);
    resolver.setPrefix("classpath:templates/");
    resolver.setSuffix(".html");
    resolver.setTemplateMode(TemplateMode.HTML);
    resolver.setCacheable(false);
    resolver.setCheckExistence(false);
    return resolver;
  }

  
  @Bean
  public SessionLocaleResolver localeResolver() {
      SessionLocaleResolver slr = new SessionLocaleResolver();
      slr.setDefaultLocale(Locale.US);
      return slr;
  }
 
  
  @Bean
  public LocaleChangeInterceptor localeChangeInterceptor() {
      LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
      lci.setParamName("lang");
      return lci;
  }
  
  @Bean
  public ResourceBundleMessageSource messageSource() {
      ResourceBundleMessageSource rbms = new ResourceBundleMessageSource();
      rbms.setBasename("i18n/message");
      return rbms;
  }
   */
}