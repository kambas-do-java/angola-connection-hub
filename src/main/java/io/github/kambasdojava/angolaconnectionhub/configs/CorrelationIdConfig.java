package io.github.kambasdojava.angolaconnectionhub.configs;

import io.github.kambasdojava.angolaconnectionhub.filters.CorrelationIdFilter;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

@Configuration
public class CorrelationIdConfig {
  @Bean
  public FilterRegistrationBean<@NonNull CorrelationIdFilter> correlationIdFilterFilterRegistrationBean() {
    FilterRegistrationBean<@NonNull CorrelationIdFilter> filterRegistrationBean = new FilterRegistrationBean<>();
    filterRegistrationBean.setFilter(new CorrelationIdFilter());
    filterRegistrationBean.addUrlPatterns("/*");
    filterRegistrationBean.setOrder(HIGHEST_PRECEDENCE);
    return filterRegistrationBean;
  }
}
