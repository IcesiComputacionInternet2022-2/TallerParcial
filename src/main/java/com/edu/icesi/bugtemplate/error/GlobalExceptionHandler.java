package com.edu.icesi.bugtemplate.error;

import com.edu.icesi.bugtemplate.error.exception.UserDemoError;
import com.edu.icesi.bugtemplate.error.exception.UserDemoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<UserDemoError> handleException(UserDemoException userDemoException){
        return new ResponseEntity<>(userDemoException.getError(), userDemoException.getHttpStatus());
    }
}
