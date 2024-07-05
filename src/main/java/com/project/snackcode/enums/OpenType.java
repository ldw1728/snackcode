package com.project.snackcode.enums;

import com.project.snackcode.converter.EnumCodeConverter;
import jakarta.persistence.Converter;
import lombok.Getter;

@Getter
public enum OpenType implements EnumCodes{

    Y("Y", "공개"),
    N("N", "비공개");

    private String code;
    private String desc;

    OpenType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Converter(autoApply = true)
    static class CodeConverter extends EnumCodeConverter<OpenType> {
        public CodeConverter() {
            super(OpenType.class);
        }
    }
}
