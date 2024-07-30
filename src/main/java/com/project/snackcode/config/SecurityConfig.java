package com.project.snackcode.config;

import com.project.snackcode.filter.JwtAuthenticationFilter;
import com.project.snackcode.filter.JwtAuthorizationFilter;
import com.project.snackcode.handler.AccessDeniedHandlerImpl;
import com.project.snackcode.repository.MemberRepository;
import com.project.snackcode.service.UserDetailsServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
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
    public AccessDeniedHandler accessDeniedHandler() {
        return new AccessDeniedHandlerImpl();
    }

    /**
     * cors 설정
     * @return
     */
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("http://localhost:7070"); //특정 ip에 허용
//        config.addAllowedHeader("*"); // 모든 header에 허용
//        config.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }

    // 정적 리소스들은 시큐리티 필터에 거치지 않도록 무시 설정.
    @Bean
    public WebSecurityCustomizer configure(){
        return (web) -> {
            web.ignoring().requestMatchers("/node_modules/**", "/favicon.ico")
                    .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                /** userDetailsService */
                .userDetailsService(userDetailsService)

                /** exception handler */
                .exceptionHandling(exception -> {
                    exception.accessDeniedHandler(accessDeniedHandler());
                })

                //.addFilter(corsFilter())
                .csrf(csrf -> csrf.disable())

                /** jwt filter */
                .addFilter(new JwtAuthenticationFilter(authenticationManager(authenticationConfiguration)))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(authenticationConfiguration), memberRepository))

                /** form login config */
                .formLogin(form->form
                        .loginPage("/login")
                        .usernameParameter("email")
                )

                /** authorize request path config */
                .authorizeHttpRequests(auth -> {
                    auth
//                            .requestMatchers("/member/join").anonymous()
                              .requestMatchers("/login").anonymous()
                              .requestMatchers("/**").authenticated();
//                            .requestMatchers( "/api/**").authenticated();
                });



        return http.build();
    }
}
