package com.project.snackcode.config;

import com.project.snackcode.listener.CustomContextListener;
import jakarta.servlet.ServletContextListener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    // regist contextListener
//    @Bean
//    public ServletListenerRegistrationBean<ServletContextListener> customListenerBean(){
//        ServletListenerRegistrationBean<ServletContextListener> bean = new ServletListenerRegistrationBean<>();
//        bean.setListener(new CustomContextListener());
//        return bean;
//    }

}
