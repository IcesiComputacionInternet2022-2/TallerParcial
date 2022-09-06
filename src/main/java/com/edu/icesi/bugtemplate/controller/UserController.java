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

@RestController
@AllArgsConstructor
public class UserController implements UserAPI {

    private final String domain = "@domain.com";
    private final String phoneExtension = "+57";
    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public UserDTO getUser(UUID userId) {
        return userMapper.fromUser(userService.getUser(userId));
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        validateEmailDomain(userDTO);
        validatePhoneExtension(userDTO);
        return userMapper.fromUser(userService.createUser(userMapper.fromDTO(userDTO)));
    }

    private void validateEmailDomain(UserDTO userDTO) {
        if (!userDTO.getEmail().endsWith(domain))
            throw new UserDemoException(HttpStatus.BAD_REQUEST, new UserDemoError(ErrorConstants.CODE_UD_01, ErrorConstants.CODE_UD_01.getMessage()));
    }

    private void validatePhoneExtension(UserDTO userDTO) {
        if (!userDTO.getPhoneNumber().startsWith(phoneExtension))
            throw new UserDemoException(HttpStatus.BAD_REQUEST, new UserDemoError(ErrorConstants.CODE_UD_02, ErrorConstants.CODE_UD_02.getMessage()));
    }

    @Override
    public List<UserDTO> getUsers() {
        return userService.getUsers().stream().map(userMapper::fromUser).collect(Collectors.toList());
    }
}
