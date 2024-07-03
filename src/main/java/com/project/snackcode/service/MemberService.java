package com.project.snackcode.service;

import com.project.snackcode.entity.Member;
import com.project.snackcode.model.member.MemberFormModel;
import com.project.snackcode.model.member.MemberModel;
import com.project.snackcode.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 조회
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public MemberModel selectModel(Long id){
        Member member = memberRepository.findById(id).get();
        if(member == null){
            return null;
        }
        return member.toModel();
    }

    /**
     * 회원 저장
     * @param memberFormModel
     */
    @Transactional
    public void save(MemberFormModel memberFormModel){
        Member member = memberFormModel.toEntity();
        memberRepository.save(member);
    }

    /**
     * 회원 수정
     * @param memberFormModel
     */
    @Transactional
    public void update(MemberFormModel memberFormModel) {
        Member member = memberRepository.findById(memberFormModel.getId()).orElseThrow();
        memberFormModel.updateEntity(member);
    }


}
