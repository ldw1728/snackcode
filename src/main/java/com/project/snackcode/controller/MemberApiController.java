package com.project.snackcode.controller;

import com.project.snackcode.model.member.MemberFormModel;
import com.project.snackcode.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberApiController {

    private final MemberService memberService;

    /**
     * 회원가입
     * @param memberFormModel
     * @return
     */
    @PostMapping("/join")
    public ResponseEntity join(@RequestBody @Valid MemberFormModel memberFormModel){
        memberService.save(memberFormModel);
        return ResponseEntity.ok().build();
    }




}
