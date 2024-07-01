package com.project.snackcode.model.member;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class MemberModel {

    private Long id;

    /** 회원명 */
    private String name;

    /** 이메일 */
    private String email;

    /** 패스워드 */
    private String password;

    /** 가입일시 */
    private LocalDateTime joinDt;

    /** 마지막 접속일시 */
    private LocalDateTime lastDt;

    /** 권한 */
    private List<MemberRoleModel> roles;

    @Builder
    public MemberModel(Long id, String name, String email, String password, LocalDateTime joinDt, LocalDateTime lastDt, List<MemberRoleModel> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.joinDt = joinDt;
        this.lastDt = lastDt;
        this.roles = roles;
    }
}
