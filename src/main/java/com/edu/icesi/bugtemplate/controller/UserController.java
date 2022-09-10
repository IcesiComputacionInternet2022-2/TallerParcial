package com.edu.icesi.bugtemplate.controller;

import com.edu.icesi.bugtemplate.api.UserAPI;
import com.edu.icesi.bugtemplate.dto.UserDTO;
import com.edu.icesi.bugtemplate.error.exception.UserDemoError;
import com.edu.icesi.bugtemplate.error.exception.UserDemoException;
import com.edu.icesi.bugtemplate.mapper.UserMapper;
import com.edu.icesi.bugtemplate.service.UserService;
import com.edu.icesi.bugtemplate.service.impl.UserServiceImpl;
import com.edu.icesi.bugtemplate.service.impl.UserServiceImplTwo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.edu.icesi.bugtemplate.constant.ErrorConstants.*;

@RestController
@AllArgsConstructor
public class UserController implements UserAPI {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;
    private final String validEmailDomainRegex = "^[A-Za-z0-9._-]+@domain\\.com$";
    private final String validCountryPhoneExtension = "+57";

    @Override
    public UserDTO getUser(UUID userId) {
        return userMapper.fromUser(userService.getUser(userId));
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        verifyValidEmailAndDomain(userMapper.fromDTO(userDTO).getEmail());
        verifyValidEmailAndCountry(userMapper.fromDTO(userDTO).getPhoneNumber());
        return userMapper.fromUser(userService.createUser(userMapper.fromDTO(userDTO)));
    }

    /**
     * This method takes care of validate both email and domain are valid
     * @param email Email to verify
     */
    private void verifyValidEmailAndDomain(String email) {
        if(!(email.matches(validEmailDomainRegex))) {
            throw new UserDemoException(HttpStatus.BAD_REQUEST, new UserDemoError(CODE_UD_01,DOMAIN));
        }
    }

    private void verifyValidEmailAndCountry(String phone) {
        if(!(phone.substring(0,3).equals(validCountryPhoneExtension))) {
            throw new UserDemoException(HttpStatus.BAD_REQUEST, new UserDemoError(CODE_UD_02,PHONE));
        }
    }

    @Override
    public List<UserDTO> getUsers() {
        return userService.getUsers().stream().map(userMapper::fromUser).collect(Collectors.toList());
    }
}
