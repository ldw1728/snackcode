package com.project.snackcode.entity;

import com.project.snackcode.converter.EncryptConverter;
import com.project.snackcode.converter.PasswordConverter;
import com.project.snackcode.enums.Role;
import com.project.snackcode.model.member.MemberModel;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  TB_MEMBER - 회원 테이블
 */

@Entity
@Table(name = "TB_MEMBER")
@DynamicUpdate
@NoArgsConstructor
@Getter
public class Member {

    /** 회원고유번호 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEM_ID")
    private Long id;

    /** 회원명 */
    @Column(name = "NAME")
    private String name;

    /** 이메일 */
    @Column(name = "EMAIL")
    private String email;

    /** 패스워드 */
    @Column(name = "PASSWORD")
    @Convert(converter = PasswordConverter.class)
    private String password;

    /** 가입일시 */
    @Column(name = "JOIN_DT")
    @CreatedDate
    private LocalDateTime joinDt;

    /** 마지막 접속일시 */
    @Column(name = "LAST_DT")
    private LocalDateTime lastDt;

    /** 권한 */
    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<MemberRole> roles = new ArrayList<>();

    // 생성
    @Builder
    public Member(Long id, String name, String email, String password) {
        this.id         = id;
        this.name       = name;
        this.email      = email;
        this.password   = password;
        this.joinDt     = LocalDateTime.now();
        this.lastDt     = LocalDateTime.now();

        roles.add(new MemberRole(this, Role.ROLE_USER));

    }

    // 수정
    public void update(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public MemberModel toModel(){
        return MemberModel.builder()
                        .id(this.id)
                        .name(this.name)
                        .email(this.email)
                        .password(this.password)
                        .joinDt(this.joinDt)
                        .lastDt(this.lastDt)
                        .roles(this.roles.stream().map(MemberRole::toModel).collect(Collectors.toList()))
                        .build();
    }
}
