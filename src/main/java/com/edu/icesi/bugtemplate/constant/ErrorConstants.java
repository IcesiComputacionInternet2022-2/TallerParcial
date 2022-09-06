package com.edu.icesi.bugtemplate.constant;


public enum ErrorConstants {
    CODE_UD_01("Email doesn't belong to @domain.com"), CODE_UD_02("Phone Number doesn't belong to Colombia");

    final String msg;

    ErrorConstants(String msg){
        this.msg = msg;
    }

    public String getMsg(){
        return msg;
    }
}
