package com.project.snackcode.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.snackcode.config.SecurityConfig;
import com.project.snackcode.model.member.MemberFormModel;
import com.project.snackcode.model.member.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;

@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    // login process url , /login 요청시 실행되는 함수
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        // username, password 받아와서 authenticationManager로 로그인 시도(principalDetailsService의 loadUserByUsername함수를 호출)
        // 권한 관리를 위해 principalDetails 객체는 세션에 저장.
        // jwt토큰 생성하여 응답

        log.debug("로그인 시도.....");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UsernamePasswordAuthenticationToken tkn = new UsernamePasswordAuthenticationToken(
                email, password
        );

        Authentication authentication = authenticationManager.authenticate(tkn);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        log.debug("로그인 성공 : {}, {}", userDetails.getMember().getId(), userDetails.getUsername());

        return authentication;

    }


    // 인증 성공 후 처리
    // jwt 토큰을 생성하여 header에 추가한다.
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {

        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();

        String jwt = JWT.create().withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + (60000 * 10))) // 만료시간 대략 10분에서 30분사이가 적당할듯
                .withClaim("id", userDetails.getMember().getId()) //jwt의 payload부분
                .sign(Algorithm.HMAC512(SecurityConfig.jwtSecretKey)); // 시크릿값은 임의로 정해준다.

        response.addHeader("Authorization", "Bearer " + jwt);

    }

}
