package com.project.snackcode.exception;

import lombok.Builder;
import lombok.Data;

@Data
public class BasicErrorException extends RuntimeException {

    private String code;
    private String msg;


    @Builder
    public BasicErrorException(String code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
        this.msg = msg;
    }

}
