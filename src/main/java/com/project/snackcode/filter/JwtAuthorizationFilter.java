package com.project.snackcode.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.project.snackcode.config.SecurityConfig;
import com.project.snackcode.entity.Member;
import com.project.snackcode.model.member.UserDetailsImpl;
import com.project.snackcode.repository.MemberRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

/**
 * BasicAuthenticationFilter - 인증이나 특정권한을 필요로하는 경로에 접근 시 꼭 거치게되는 필터
 * header에 있는 jwt가 유효한지 검증.
 */
@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private MemberRepository memberRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, MemberRepository memberRepository) {
        super(authenticationManager);
        this.memberRepository = memberRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        log.debug("인증이나 권한이 필요 : {}", request.getRequestURL());

        String jwtStr = request.getHeader("authorization");
        log.debug("JWT : {}", jwtStr);

        if (StringUtils.isBlank(jwtStr) || !jwtStr.startsWith("Bearer")) {
            chain.doFilter(request, response);
            return;
        }

        jwtStr = jwtStr.replace("Bearer ", "");
        String memId = String.valueOf(
                 JWT.require(Algorithm.HMAC512(SecurityConfig.jwtSecretKey))
                    .build()
                    .verify(jwtStr)
                    .getClaim("id")
        );

        log.debug("extracted mem_id : {}", memId);

        Member member = memberRepository.findById(Long.valueOf(memId)).get();

        // 회원 정상 조회일 경우
        if (member != null) {

            UserDetailsImpl userDetails = new UserDetailsImpl(member.toModel());
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            log.debug("jwt auth success : {}, {}", userDetails.getMember().getId(), userDetails.getUsername());

        }

        chain.doFilter(request, response);
    }
}
