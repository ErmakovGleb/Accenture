package com.accenture.itfactory.configurations;

import com.accenture.itfactory.servlets.*;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfiguration {
    @Bean
    public ServletRegistrationBean mainServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new MainServlet(), "/main");
        bean.setLoadOnStartup(1);
        return bean;
    }

    @Bean
    public ServletRegistrationBean loginServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new LoginServlet(), "/login");
        bean.setLoadOnStartup(1);
        return bean;
    }

    @Bean
    public ServletRegistrationBean registrationServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new RegistrationServlet(), "/registration");
        bean.setLoadOnStartup(1);
        return bean;
    }

    @Bean
    public ServletRegistrationBean adminServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new AdminServlet(), "/admin");
        bean.setLoadOnStartup(1);
        return bean;
    }

    @Bean
    public ServletRegistrationBean adminQuestionsServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new AdminQuestionsServlet(), "/admin/question");
        bean.setLoadOnStartup(1);
        return bean;
    }
}
