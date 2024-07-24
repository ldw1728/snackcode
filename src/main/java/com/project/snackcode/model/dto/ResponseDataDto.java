package com.project.snackcode.model.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseDataDto {

    private HttpStatus status;

    private Object data;

    private String code;

    private String msg;

    private String returnUrl;

    @Builder
    public ResponseDataDto(HttpStatus status, Object data, String code, String msg, String returnUrl) {
        this.status = status;
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.returnUrl = returnUrl;
    }
}
