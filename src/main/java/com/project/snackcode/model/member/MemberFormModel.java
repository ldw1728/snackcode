package com.project.snackcode.model.member;

import com.project.snackcode.entity.Member;
import lombok.Data;

@Data
public class MemberFormModel {
    private String name;
    private String email;
    private String password;

    public Member toEntity(){
        return Member.builder().name(this.name).email(this.email).password(this.password).build();
    }

    public void updateEntity(Member member) {
        member.update(this.name, this.email, this.password);
    }
}
