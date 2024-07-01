package com.project.snackcode.entity;

import com.project.snackcode.enums.Role;
import com.project.snackcode.model.member.MemberRoleModel;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class MemberRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mem_id")
    private Member member;

    @Column(name = "ROLE_TYPE")
    private Role role;

    @Builder
    public MemberRole(Member member, Role role) {
        this.member = member;
        this.role = role;
    }

    public MemberRoleModel toModel(){
        return MemberRoleModel.builder()
                                .seq(this.seq)
                                .memId(this.member.getId())
                                .role(this.role)
                                .build();
    }

}
