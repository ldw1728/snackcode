package com.project.snackcode.config;

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


    // 정적 리소스들을 적용
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**").addResourceLocations(
//                "classpath:/static/**",
//                "classpath:/resources/**"
//        );
//    }
}
