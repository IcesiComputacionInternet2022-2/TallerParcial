package com.edu.icesi.bugtemplate.controller;

import com.edu.icesi.bugtemplate.api.UserAPI;
import com.edu.icesi.bugtemplate.constant.ErrorConstants;
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

    @Override
    public UserDTO getUser(UUID userId) {
        return userMapper.fromUser(userService.getUser(userId));
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {

        validateEmail(userDTO.getEmail());
        validatePhoneNumber(userDTO.getPhoneNumber());
        return userMapper.fromUser(userService.createUser(userMapper.fromDTO(userDTO)));
    }

    @Override
    public List<UserDTO> getUsers() {
        return userService.getUsers().stream().map(userMapper::fromUser).collect(Collectors.toList());
    }

    private void validateEmail(String email) {
        String regex = "[A-Za-z\\\\d]+@domain.com";
        if(!email.matches(regex)){
            throw new UserDemoException(HttpStatus.BAD_REQUEST, new UserDemoError(ErrorConstants.C01.getCode(), ErrorConstants.C01.getMsj()));
        }
    }

    private void validatePhoneNumber(String phoneNumber){
        String regex = "String regex = \"^\\\\+57[\\\\s\\\\S]*\";";
        if(!phoneNumber.matches(regex)){
            throw new UserDemoException(HttpStatus.BAD_REQUEST, new UserDemoError(ErrorConstants.C02.getCode(), ErrorConstants.C02.getMsj()));
        }
    }

}
