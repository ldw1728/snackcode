package com.project.snackcode.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

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
@EntityListeners(AuditingEntityListener.class) // createdDate, modifiedDate 생성을 위해 필요
@Getter
public class Member {

    /** 회원고유번호 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEM_ID")
    private Long id;

    /** 회원명 */
    @Column(name = "NAME", nullable = false)
    private String name;

    /** 이메일 */
    @Column(name = "EMAIL", nullable = false)
    private String email;

    /** 패스워드 */
    @Column(name = "PASSWORD", nullable = false)
    @Convert(converter = PasswordConverter.class)
    private String password;

    /** 가입일시 */
    @Column(name = "JOIN_DT", nullable = false)
    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime joinDt;

    /** 마지막 접속일시 */
    @Column(name = "LAST_DT", nullable = false)
    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
        //updateLastDt();

        roles.add(new MemberRole(this, Role.ROLE_USER));

    }

    // 수정
    public void update(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void updateLastDt(){
        this.lastDt = LocalDateTime.now();
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

    public MemberModel toBasicModel(){
        return MemberModel.builder()
                .id(this.id)
                .name(this.name)
                .email(this.email)
                .build();
    }
}
