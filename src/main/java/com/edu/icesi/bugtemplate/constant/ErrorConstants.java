package com.edu.icesi.bugtemplate.constant;

public enum ErrorConstants {
    C01("Email doesn't belong to @domain.com","CODE_UD_01"),
    C02("Phone Number doesn't belong to Colombia","CODE_UD_02");

    private final String msj;
    private final String code;

    ErrorConstants(String msj, String code) {
        this.msj = msj;
        this.code = code;
    }

    public String getMsj() {
        return msj;
    }

    public String getCode() {
        return code;
    }
}
