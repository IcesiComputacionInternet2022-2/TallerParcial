package com.edu.icesi.bugtemplate.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorConstants {
    CODE_UD_01("Email doesn't belong to @domain.com"),
    CODE_UD_02("Phone Number doesn't belong to Colombia");

    private final String message;
}
