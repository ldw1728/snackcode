package com.project.snackcode.config;

import com.project.snackcode.filter.JwtAuthenticationFilter;
import com.project.snackcode.filter.JwtAuthorizationFilter;
import com.project.snackcode.repository.MemberRepository;
import com.project.snackcode.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Random;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;
    private final AuthenticationConfiguration authenticationConfiguration;
    private final MemberRepository memberRepository;

    //public static final String jwtSecretKey = createSecretKey();
    public static final String jwtSecretKey = "123";


    /**
     * jwt secretkey 생성
     * @return
     */
    private static String createSecretKey() {
        Random random = new Random();
        byte[] bytes = new byte[random.nextInt(20) + 10]; // 10 ~ 20 자리수를 랜덤으로 추출한다.
        random.nextBytes(bytes); // 임의의 문자열을 추출
        String scrtKey = new String(Base64.getEncoder().encode(bytes), Charset.forName("UTF-8")); // 추출한 문자열을 암호화
        log.debug("[ jwtSecretKey ] : {}", scrtKey);
        return scrtKey;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.userDetailsService(userDetailsService);
        http.csrf(csrf -> csrf.disable())
                .addFilter(new JwtAuthenticationFilter(authenticationManager(authenticationConfiguration)))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(authenticationConfiguration), memberRepository))
            .formLogin(form->form.loginProcessingUrl("/login").usernameParameter("email"))
            .authorizeHttpRequests(auth -> {
                        auth
                        .requestMatchers("/api/member/join").anonymous()
                        .requestMatchers( "/api/**").access(new WebExpressionAuthorizationManager("hasRole('ROLE_USER')"))
                        .requestMatchers("/auth").authenticated()
                        .requestMatchers("/home").permitAll();

            });



        return http.build();
    }
}
