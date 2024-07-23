package com.project.snackcode.model.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorDto {

    private HttpStatus status;

    private Object data;

    private String msg;

    private String returnUrl;

    @Builder
    public ErrorDto(HttpStatus status, Object data, String msg, String returnUrl) {
        this.status = status;
        this.data = data;
        this.msg = msg;
        this.returnUrl = returnUrl;
    }
}
