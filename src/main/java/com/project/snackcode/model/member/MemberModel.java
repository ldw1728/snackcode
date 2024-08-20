package com.project.snackcode.model.member;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@SuperBuilder
public class MemberModel extends MemberBaseModel {

    /** 패스워드 */
    private String password;

    /** 가입일시 */
    private LocalDateTime joinDt;

    /** 마지막 접속일시 */
    private LocalDateTime lastDt;

    /** 권한 */
    private List<MemberRoleModel> roles;

    public MemberModel(Long id, String name, String email, String password, LocalDateTime joinDt, LocalDateTime lastDt, List<MemberRoleModel> roles) {
        super(id, name, email);
        this.password   = password;
        this.joinDt     = joinDt;
        this.lastDt     = lastDt;
        this.roles      = roles;
    }
}
