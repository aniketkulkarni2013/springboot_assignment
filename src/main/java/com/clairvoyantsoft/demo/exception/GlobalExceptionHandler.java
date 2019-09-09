package com.clairvoyantsoft.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;


public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    public ResponseEntity<Object> handleAccessDenied(Exception exception, WebRequest webRequest){

        return  new ResponseEntity<Object>("Access is Denied.", HttpStatus.FORBIDDEN);
    }
}
