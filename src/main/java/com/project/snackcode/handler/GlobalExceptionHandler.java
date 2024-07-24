package com.project.snackcode.handler;

import com.project.snackcode.exception.BasicErrorException;
import com.project.snackcode.model.dto.ResponseDataDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BasicErrorException.class)
    private ResponseEntity<ResponseDataDto> basicErrorException(BasicErrorException exception) {
        return new ResponseEntity(
                ResponseDataDto.builder().msg(exception.getMessage()).code(exception.getCode()).build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<ResponseDataDto> commException(Exception e) {
        e.printStackTrace();
        return new ResponseEntity(
                ResponseDataDto.builder().msg(e.getMessage()).build(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
