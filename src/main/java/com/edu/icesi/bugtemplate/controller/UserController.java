package com.edu.icesi.bugtemplate.controller;

import com.edu.icesi.bugtemplate.api.UserAPI;
import com.edu.icesi.bugtemplate.dto.UserDTO;
import com.edu.icesi.bugtemplate.mapper.UserMapper;
import com.edu.icesi.bugtemplate.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
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
        return userMapper.fromUser(userService.createUser(userMapper.fromDTO(userDTO)));
    }

    @Override
    public List<UserDTO> getUsers() {
        return userService.getUsers().stream().map(userMapper::fromUser).collect(Collectors.toList());
    }
}
