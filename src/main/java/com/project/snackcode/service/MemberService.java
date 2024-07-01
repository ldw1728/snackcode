package com.project.snackcode.service;

import com.project.snackcode.model.member.MemberFormModel;
import com.project.snackcode.entity.Member;
import com.project.snackcode.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void join(MemberFormModel memberFormModel){
        Member member = memberFormModel.toEntity();
        memberRepository.save(member);
    }

}
