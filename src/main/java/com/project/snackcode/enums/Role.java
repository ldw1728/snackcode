package com.project.snackcode.enums;


import com.project.snackcode.converter.EnumCodeConverter;
import jakarta.persistence.Converter;
import lombok.Getter;

@Getter
public enum Role implements EnumCodes{

    ROLE_USER("USER", "일반회원"),
    ROLE_ADMIN("ADMIN", "관리자");

    private String code;
    private String desc;

    Role(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Converter(autoApply = true)
    static class CodeConverter extends EnumCodeConverter<Role> {
        public CodeConverter() {
            super(Role.class);
        }
    }
}
