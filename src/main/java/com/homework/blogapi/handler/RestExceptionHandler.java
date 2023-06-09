package com.homework.blogapi.handler;

import com.homework.blogapi.error.ErrorDetail;
import com.homework.blogapi.error.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException hmnre, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(status.value());
        errorDetail.setTitle("Message not readable :(");
        errorDetail.setDetail(hmnre.getMessage());
        errorDetail.setDeveloperMessage(hmnre.getClass().getName());
        return handleExceptionInternal(hmnre, errorDetail, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manve, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDetail errorDetail= new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
        String requestPath = (String) request.getAttribute("javax.servlet.error.request_uri", 1);
        if(requestPath == null){
            requestPath = request.getHeader("Header");
        }
        errorDetail.setTitle("Validation failed :(");
        errorDetail.setDetail("Input validation failed :(");
        errorDetail.setDeveloperMessage(manve.getClass().getName());
        List<FieldError> fieldErrors = manve.getBindingResult().getFieldErrors();
        for (FieldError fe : fieldErrors) {
            List<ValidationError> validationErrorList = errorDetail.getErrors().get(fe.getField());
            if (validationErrorList == null){
                validationErrorList = new ArrayList<ValidationError>();
                errorDetail.getErrors().put(fe.getField(), validationErrorList);
            }
            ValidationError validationError = new ValidationError();
            validationError.setCode(fe.getCode());
            validationError.setMessage(messageSource.getMessage(fe, null));
            validationErrorList.add(validationError);
        }
        return handleExceptionInternal(manve, errorDetail, headers, status, request);
    }

}
