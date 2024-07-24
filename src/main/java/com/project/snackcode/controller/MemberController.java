package com.project.snackcode.controller;

import com.project.snackcode.model.member.MemberFormModel;
import com.project.snackcode.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    /**
     * 로그인 페이지
     * @return
     */
    @GetMapping("/login")
    public String login(){
        return "pages/member/login";
    }

    /**
     * 회원가입 페이지
     * @return
     */
    @GetMapping("/member/join")
    public String join() {
        return "pages/member/join";
    }

    /**
     * 회원가입 api
     * @param memberFormModel
     * @return
     */
    @PostMapping("/member/join")
    @ResponseBody
    public ResponseEntity join(@Valid MemberFormModel memberFormModel){
        memberService.save(memberFormModel);
        return ResponseEntity.ok().build();
    }




}
