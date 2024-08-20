package com.project.snackcode.model.member;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class MemberBaseModel {

    private Long id;

    /** 회원명 */
    private String name;

    /** 이메일 */
    private String email;

    public MemberBaseModel(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
