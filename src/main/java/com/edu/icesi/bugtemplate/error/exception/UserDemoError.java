package com.edu.icesi.bugtemplate.error.exception;

import com.edu.icesi.bugtemplate.constant.ErrorConstants;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDemoError {
    private ErrorConstants code;
    private String message;
}
