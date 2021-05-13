package com.alquiler.reservas.internationalization;


import java.util.List;
import java.util.Locale;

import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.i18n.LocaleContextResolver;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.SimpleLocaleContext;

public class LocaleResolver implements LocaleContextResolver {

  @Override
  public LocaleContext resolveLocaleContext(ServerWebExchange exchange) {
    String language = exchange.getRequest().getHeaders().getFirst("Accept-Language");

    Locale targetLocale = Locale.getDefault();
    if (language != null && !language.isEmpty()) {
      targetLocale = Locale.forLanguageTag(language);
    }
    return new SimpleLocaleContext(targetLocale);
  }

  @Override
  public void setLocaleContext(ServerWebExchange exchange, LocaleContext localeContext) {
    throw new UnsupportedOperationException("Not Supported");
  }

}