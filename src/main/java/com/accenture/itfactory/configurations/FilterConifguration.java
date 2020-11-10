package com.accenture.itfactory.configurations;

import com.accenture.itfactory.filters.AdminFilter;
import com.accenture.itfactory.filters.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConifguration {

    @Bean
    public FilterRegistrationBean adminFilter() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new AdminFilter());
        registration.addUrlPatterns("/admin");
        registration.addUrlPatterns("/admin/*");
        registration.setName("adminFilter");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public FilterRegistrationBean loginFilter() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new LoginFilter());
        registration.addUrlPatterns("/login");
        registration.addUrlPatterns("/register");
        registration.addUrlPatterns("/main");
        registration.setName("loginFilter");
        registration.setOrder(1);
        return registration;
    }
}
