package com.project.snackcode.model.member;

import com.project.snackcode.entity.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MemberFormModel {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    public Member toEntity(){
        return Member.builder().name(this.name).email(this.email).password(this.password).build();
    }

    public void updateEntity(Member member) {
        member.update(this.name, this.email, this.password);
    }
}
