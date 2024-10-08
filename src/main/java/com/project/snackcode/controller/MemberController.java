package com.project.snackcode.controller;

import com.project.snackcode.model.member.LoginContextHolder;
import com.project.snackcode.model.member.MemberChngPwdFormModel;
import com.project.snackcode.model.member.MemberFormModel;
import com.project.snackcode.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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

    /**
     * 비밀번호 변경
     * @param memberChngPwdFormModel
     * @return
     */
    @PostMapping("/member/pwd")
    @ResponseBody
    public ResponseEntity changePassword(@Valid MemberChngPwdFormModel memberChngPwdFormModel) {
        memberService.changePassword(memberChngPwdFormModel);
        return ResponseEntity.noContent().build();
    }

    /**
     * 회원 탈퇴
     * @return
     */
    @DeleteMapping("/member/secession")
    public void secession(HttpServletResponse response) throws IOException {
        Long id = LoginContextHolder.getLoginUser().getId();
        memberService.delete(id);
        response.sendRedirect("/logout");
    }


}
