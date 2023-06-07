package com.homework.blogapi.handler;

import com.homework.blogapi.error.ErrorDetail;
import com.homework.blogapi.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setTitle("Resource not found take the L");
        String errorMessage = ex.getMessage();
        errorDetail.setDetail(errorMessage);
        errorDetail.setDeveloperMessage("boo you stink");
        return new ResponseEntity<>(errorMessage + errorDetail, HttpStatus.NOT_FOUND);
    }

}
