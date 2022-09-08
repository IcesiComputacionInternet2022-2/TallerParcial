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
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@RestController
public class UserController implements UserAPI {


    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public UserDTO getUser(UUID userId) {
        return userMapper.fromUser(userService.getUser(userId));
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        checkEmailDomain(userDTO);
        checkPhoneNumber(userDTO);

        return userMapper.fromUser(userService.createUser(userMapper.fromDTO(userDTO)));
    }
    public void checkEmailDomain(UserDTO userDTO){
        if(!userDTO.getEmail().split("@")[1].equals("domain.com")){
            throw new UserDemoException(HttpStatus.BAD_REQUEST, new UserDemoError("CODE_UD_01", ErrorConstants.CODE_UD_01.getMsg()));
        }
    }
    public void checkPhoneNumber(UserDTO userDTO){
        if(!userDTO.getPhoneNumber().substring(0,3).equals("+57")){
            throw new UserDemoException(HttpStatus.BAD_REQUEST, new UserDemoError("CODE_UD_02", ErrorConstants.CODE_UD_02.getMsg()));
        }
    }


    @Override
    public List<UserDTO> getUsers() {
        return userService.getUsers().stream().map(userMapper::fromUser).collect(Collectors.toList());
    }
}
