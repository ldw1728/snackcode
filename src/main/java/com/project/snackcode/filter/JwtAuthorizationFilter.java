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
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.Arrays;

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

        try {

            log.debug(" ----- jwt validation check ------ url : [{}]", request.getRequestURL());

            String jwtStr = request.getHeader("authorization");

            // 요청 헤더에 token이 존재하는지
            if (StringUtils.isBlank(jwtStr) || !jwtStr.startsWith("Bearer")) {
                throw new Exception("JWT token is not exist");
            }

            log.debug("JWT : {}", jwtStr);

            jwtStr = jwtStr.replace("Bearer ", "");

            // jwt 검증 후 payload영역의 회원 id 데이터 추출
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

                // 최근활동일자 수정
                member.updateLastDt();

                // authentication 객체 생성 후 security session에 강제로 설정(인증처리)
                UserDetailsImpl userDetails = new UserDetailsImpl(member.toModel());
                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                userDetails.getAuthorities().forEach(str -> System.out.println(str.getAuthority()));
                log.debug("jwt auth success : {}, {}", userDetails.getMember().getId(), userDetails.getUsername());

            }

        }catch(Exception e){
            log.warn(e.getMessage());
        }finally {
            chain.doFilter(request, response);
        }
        // 토큰이 없거나 검증실패 시 예외가 발생. 따로 처리될것없이 다음 필터로 넘겨준다
        // 인증이 필요하다면 로그인페이지로, 아니면 정상적으로 페이지 로드함.
    }
}
