package com.edu.icesi.bugtemplate.constant;

import com.edu.icesi.bugtemplate.controller.UserController;
import lombok.Getter;

public enum ErrorConstants {
    CODE_UD_01(String.format("Email doesn't belong to %s", UserController.DOMAIN)),
    CODE_UD_02("Phone Number doesn't belong to Colombia");

    private String message;
    ErrorConstants(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
