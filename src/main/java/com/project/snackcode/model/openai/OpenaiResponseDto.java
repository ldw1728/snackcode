package com.project.snackcode.model.openai;

import lombok.Builder;
import lombok.Data;

@Data
public class OpenaiResponseDto {
    private String desc;
    private String code;
    private String cntnsType;

    @Builder
    public OpenaiResponseDto(String desc, String code, String cntnsType) {
        this.desc = desc;
        this.code = code;
        this.cntnsType = cntnsType;
    }
}
