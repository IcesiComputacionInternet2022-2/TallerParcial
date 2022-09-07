package com.edu.icesi.bugtemplate.controller;

import com.edu.icesi.bugtemplate.api.UserAPI;
import com.edu.icesi.bugtemplate.constant.Constants;
import com.edu.icesi.bugtemplate.constant.ErrorConstants;
import com.edu.icesi.bugtemplate.dto.UserDTO;
import com.edu.icesi.bugtemplate.error.exception.UserDemoError;
import com.edu.icesi.bugtemplate.error.exception.UserDemoException;
import com.edu.icesi.bugtemplate.mapper.UserMapper;
import com.edu.icesi.bugtemplate.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class UserController implements UserAPI {


    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public UserDTO getUser(UUID userId) {
        return userMapper.fromUser(userService.getUser(userId));
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        String validationResult = validateEmailDomain(userDTO.getEmail())+validateColombianPhoneNumber(userDTO.getPhoneNumber());
        if(validationResult.equals("")){
            return userMapper.fromUser(userService.createUser(userMapper.fromDTO(userDTO)));
        }else{
            String[] exceptionsParts = getExceptionMsg(validationResult).split(Constants.SPLIT_EXCEPTIONS);

            throw new UserDemoException(HttpStatus.BAD_REQUEST, new UserDemoError(exceptionsParts[0], exceptionsParts[1]));
        }

    }

    @Override
    public List<UserDTO> getUsers() {
        return userService.getUsers().stream().map(userMapper::fromUser).collect(Collectors.toList());
    }

    public String validateEmailDomain(String email){
        return (email.split("@")[1].equals(Constants.CORRECT_DOMAIN))?"":ErrorConstants.CODE_UD_01+Constants.SPLIT_EXCEPTION_CODE_MSG+ ErrorConstants.DOMAIN_ERROR +Constants.SPLIT_EXCEPTIONS;
    }

    public String validateColombianPhoneNumber(String phoneNumber){
        return (phoneNumber.substring(0,3).equals(Constants.COLOMBIAN_PHONE_HUMBER))?"":ErrorConstants.CODE_UD_02+Constants.SPLIT_EXCEPTION_CODE_MSG+ErrorConstants.PHONE_NUMBER_ERROR+Constants.SPLIT_EXCEPTIONS;
    }

    public String getExceptionMsg(String entireMsg){

        String[] parts = entireMsg.split(Constants.SPLIT_EXCEPTIONS);

        String codes = "";
        String msgs = "";

        for(String str : parts){
            String[] exceptionParts = str.split(Constants.SPLIT_EXCEPTION_CODE_MSG);
            codes+= exceptionParts[0];
            msgs+= exceptionParts[1];
        }

        return codes+Constants.SPLIT_EXCEPTIONS+msgs;
    }
}
