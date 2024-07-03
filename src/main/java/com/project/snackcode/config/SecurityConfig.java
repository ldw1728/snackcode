package com.project.snackcode.config;

import com.project.snackcode.filter.JwtAuthenticationFilter;
import com.project.snackcode.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;
    private final AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.userDetailsService(userDetailsService);
        http.csrf(csrf -> csrf.disable())
                .addFilter(new JwtAuthenticationFilter(authenticationManager(authenticationConfiguration)))
            .formLogin(form->form.loginProcessingUrl("/login"))
                .httpBasic(basic->basic.disable())
            .authorizeHttpRequests(auth -> {
                auth.requestMatchers(HttpMethod.POST, "/api/member/join").permitAll()
                     .requestMatchers( "/auth/**").access(new WebExpressionAuthorizationManager("hasRole('ROLE_USER')"))
                    .requestMatchers("/**").permitAll();

            });



        return http.build();
    }
}
