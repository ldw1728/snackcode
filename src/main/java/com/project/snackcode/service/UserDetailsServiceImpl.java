package com.project.snackcode.service;

import com.project.snackcode.entity.Member;
import com.project.snackcode.model.member.UserDetailsImpl;
import com.project.snackcode.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.findByEmail(username);

        if(member == null){
            throw new UsernameNotFoundException("회원을 찾을 수 없음.");
        }

        log.debug("조회된 회원 : {}, {}", member.getId(), member.getEmail());

        UserDetailsImpl userDetails = new UserDetailsImpl(member.toModel());

        return userDetails;
    }
}
