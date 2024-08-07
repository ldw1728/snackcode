package com.project.snackcode.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.snackcode.config.SecurityConfig;
import com.project.snackcode.model.dto.ResponseDataDto;
import com.project.snackcode.model.member.MemberModel;
import com.project.snackcode.model.member.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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

        try {
            //String email = request.getParameter("email");
            //String password = request.getParameter("password");
            ObjectMapper om = new ObjectMapper();
            MemberModel member = om.readValue(request.getInputStream(), MemberModel.class);

            log.debug("email, password : {}, {}", member.getEmail(), member.getPassword());

            UsernamePasswordAuthenticationToken tkn = new UsernamePasswordAuthenticationToken(
                    member.getEmail(), member.getPassword()
            );

            Authentication authentication = authenticationManager.authenticate(tkn);

            //UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            return authentication;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (BadCredentialsException b) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }

        return null;


    }


    // 인증 성공 후 처리
    // jwt 토큰을 생성하여 header에 추가한다.
    // front framework -> thymeleaf로 변경하여 쿠키 방식으로 적용
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();

        String jwt = JWT.create().withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + (60000 * 100))) // 만료시간 대략 10분에서 30분사이가 적당할듯
                .withClaim("id", userDetails.getMember().getId()) //jwt의 payload부분
                .sign(Algorithm.HMAC512(SecurityConfig.jwtSecretKey)); // 시크릿값은 임의로 정해준다.

        log.debug("====== create Token : {}", jwt);

        // header에 추가
        //response.addHeader("Authorization", "Bearer " + jwt);

        // 쿠키 생성
        for (Cookie cookie : request.getCookies()) {
            if ("Authorization".equals(cookie.getName())) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }

        ResponseCookie responseCookie = ResponseCookie.from("Authorization", URLEncoder.encode("Bearer " + jwt, StandardCharsets.UTF_8))
                .path("/")
                .maxAge(60000 * 100)
                .httpOnly(true)
                .secure(true)
                .sameSite("None")
                .build();

        response.addHeader("Set-cookie", responseCookie.toString());

//        Cookie cookie = new Cookie("Authorization", );
//        cookie.setMaxAge(60000 * 100);
//        //cookie.setDomain("localhost");
//        cookie.setPath("/");
//        cookie.setSecure(true);
//        cookie.setHttpOnly(true);
//        response.addCookie(cookie);
        log.debug("====== create jwt cookie");

        //request.getRequestDispatcher("/home").forward(request, response);
//        String redirectUrl = request.getParameter("redirectUrl");
//        SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);
//
//        if(redirectUrl == null){
//            redirectUrl = savedRequest != null ? savedRequest.getRedirectUrl() : "/home";
//        }
//
//        response.addHeader("redirectUrl", redirectUrl);

    }

}
