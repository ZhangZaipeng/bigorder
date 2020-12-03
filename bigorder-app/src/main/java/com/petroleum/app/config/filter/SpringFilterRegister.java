package com.petroleum.app.config.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringFilterRegister {


  @Bean
  public FilterRegistrationBean NoCacheFilterRegistration() {
    FilterRegistrationBean registration = new FilterRegistrationBean();
    registration.setFilter(new NoCacheFilter());

    registration.setName("noCacheFilter");
    registration.setOrder(10);
    return registration;
  }

}
