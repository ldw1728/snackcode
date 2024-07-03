package com.project.snackcode.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.snackcode.model.member.MemberFormModel;
import com.project.snackcode.model.member.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.Random;

@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final String jwtSecretKey;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        jwtSecretKey = createSecretKey();
        log.debug("***********  jwt secretKey = {}", jwtSecretKey);
    }

    // login process url , /login 요청시 실행되는 함수
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        // username, password 받아와서 authenticationManager로 로그인 시도(principalDetailsService의 loadUserByUsername함수를 호출)
        // 권한 관리를 위해 principalDetails 객체는 세션에 저장.
        // jwt토큰 생성하여 응답

        try {

            log.debug("로그인 시도.....");

            ObjectMapper om = new ObjectMapper();
            MemberFormModel memberFormModel = om.readValue(request.getInputStream(), MemberFormModel.class);

            UsernamePasswordAuthenticationToken tkn = new UsernamePasswordAuthenticationToken(
                    memberFormModel.getEmail(), memberFormModel.getPassword()
            );

            Authentication authentication = authenticationManager.authenticate(tkn);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            log.debug("로그인 성공 : {}, {}", userDetails.getMember().getId(), userDetails.getUsername());

            return authentication;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    // 인증 성공 후 처리
    // jwt 토큰을 생성하여 header에 추가한다.
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult){

        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();

        String jwt = JWT.create().withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + (60000*10))) // 만료시간 대략 10분에서 30분사이가 적당할듯
                .withClaim("id", userDetails.getMember().getId()) //jwt의 payload부분
                .sign(Algorithm.HMAC512(jwtSecretKey)); // 시크릿값은 임의로 정해준다.

        response.addHeader("Authorization", "bearer " + jwt);

    }

    private String createSecretKey(){
        Random random = new Random();
        byte[] bytes = new byte[random.nextInt(20) + 10];
        random.nextBytes(bytes);
        return new String(Base64.getEncoder().encode(bytes),  Charset.forName("UTF-8"));
    }
}
