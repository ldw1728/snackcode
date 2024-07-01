package com.project.snackcode.model.member;

import com.project.snackcode.entity.Member;
import com.project.snackcode.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
public class MemberRoleModel {

    private Long seq;

    private Long memId;

    private Role role;

    @Builder
    public MemberRoleModel(Long seq, Long memId, Role role) {
        this.seq    = seq;
        this.memId  = memId;
        this.role   = role;
    }
}
