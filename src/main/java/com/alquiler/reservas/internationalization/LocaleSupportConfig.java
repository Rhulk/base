package com.alquiler.reservas.internationalization;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.i18n.LocaleContextResolver;
//import org.springframework.web.reactive.config.DelegatingWebFluxConfiguration;

import com.alquiler.reservas.internationalization.LocaleResolver;

@Configuration
public class LocaleSupportConfig  {

  protected LocaleContextResolver createLocaleContextResolver() {
    return new LocaleResolver();
  }

}
