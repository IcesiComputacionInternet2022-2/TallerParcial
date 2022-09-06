package com.edu.icesi.bugtemplate.controller;

import com.edu.icesi.bugtemplate.api.UserAPI;
import com.edu.icesi.bugtemplate.dto.UserDTO;
import com.edu.icesi.bugtemplate.error.exception.UserDemoError;
import com.edu.icesi.bugtemplate.error.exception.UserDemoException;
import com.edu.icesi.bugtemplate.mapper.UserMapper;
import com.edu.icesi.bugtemplate.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class UserController implements UserAPI {


    private final UserService userService;
    private final UserMapper userMapper;

    private static final String DOMAIN = "domain.com";


    @Override
    public UserDTO getUser(UUID userId) {
        return userMapper.fromUser(userService.getUser(userId));
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        validateUser(userDTO);
        return userMapper.fromUser(userService.createUser(userMapper.fromDTO(userDTO)));
    }

    @Override
    public List<UserDTO> getUsers() {
        return userService.getUsers().stream().map(userMapper::fromUser).collect(Collectors.toList());
    }

    private void validateUser(UserDTO userDTO){

        boolean isValidUser;
        UserValidations fieldValidation = validateUserNulls(userDTO);
        switch (fieldValidation){
            case NOT_NULLS:
                isValidUser = validateUserEmail(userDTO) && validateUserPhoneNumber(userDTO) && validateUserNames(userDTO);
                break;

            case EMAIL_NULL:
                isValidUser = validateUserPhoneNumber(userDTO) && validateUserNames(userDTO);
                break;

            case PHONE_NULL:
                isValidUser = validateUserEmail(userDTO) & validateUserNames(userDTO);
                break;

            case EMAIL_PHONE_NULL:
                break;
        }

    }

    private UserValidations validateUserNulls(UserDTO userDTO){
        UserValidations fieldValidation = UserValidations.EMAIL_PHONE_NULL;
        if(validateUserEmailNotNull(userDTO.getEmail()) && validateUserPhoneNotNull(userDTO.getPhoneNumber())){
            fieldValidation = UserValidations.NOT_NULLS;
        }

        if(!validateUserEmailNotNull(userDTO.getEmail()) && validateUserPhoneNotNull(userDTO.getPhoneNumber())){
            fieldValidation = UserValidations.EMAIL_NULL;
        }

        if(validateUserEmailNotNull(userDTO.getEmail()) && !validateUserPhoneNotNull(userDTO.getPhoneNumber())){
            fieldValidation = UserValidations.PHONE_NULL;
        }

        return fieldValidation;
    }

    private boolean validateUserPhoneNotNull(String phoneNumber) {
        return phoneNumber != null;
    }

    private boolean validateUserEmailNotNull(String email) {
        return email != null;
    }

    private boolean validateUserNames(UserDTO userDTO) {
        return validateUserNamesSize(userDTO) && validateUserNamesContent(userDTO);
    }

    private boolean validateUserNamesContent(UserDTO userDTO) {
        return userDTO.getFirstName().matches("[a-zA-Z]+") && userDTO.getLastName().matches("[a-zA-Z]+");
    }

    private boolean validateUserNamesSize(UserDTO userDTO) {
        return userDTO.getFirstName().length() <= 120 && userDTO.getLastName().length() <= 120;
    }

    private boolean validateUserPhoneNumber(UserDTO userDTO) {
        return validateUserPhoneNumberExtension(userDTO) && validateUserPhoneNumberContent(userDTO);
    }

    private boolean validateUserPhoneNumberContent(UserDTO userDTO) {
        String phoneNumber = userDTO.getPhoneNumber();
        return phoneNumber.length() == "+57XXXXXXXXXX".length() && phoneNumber.replace("+","").matches("[0-9]+");
    }

    private boolean validateUserPhoneNumberExtension(UserDTO userDTO) {
        if (!userDTO.getPhoneNumber().startsWith("+57")){
            throw new UserDemoException(HttpStatus.BAD_REQUEST, new UserDemoError("CODE_UD_02", "Phone Number doesn't belong to Colombia"));
        }
        return true;
    }

    private boolean validateUserEmail(UserDTO userDTO){
        String[] email = userDTO.getEmail().split("@");
        if(email.length == 2){
            System.out.println('@' + email[1]);
            return  validateUserEmailDomain(email[1]) && validateUserEmailSpecialChars(email[0]);
        }
        throw new UserDemoException(HttpStatus.BAD_REQUEST, new UserDemoError("code", "error"));
    }

    private boolean validateUserEmailSpecialChars(String user) {
        return user.matches("[a-zA-Z0-9]+");
    }

    private boolean validateUserEmailDomain(String domain) {
        if(!(domain.equals(DOMAIN))){
            throw new UserDemoException(HttpStatus.BAD_REQUEST, new UserDemoError("CODE_UD_01", "Email doesn't belong to @domain.com"));
        }
        return true;
    }


}
