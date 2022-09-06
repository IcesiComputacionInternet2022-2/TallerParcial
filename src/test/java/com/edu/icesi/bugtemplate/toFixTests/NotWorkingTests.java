package com.edu.icesi.bugtemplate.toFixTests;

import com.edu.icesi.bugtemplate.controller.UserController;
import com.edu.icesi.bugtemplate.dto.UserDTO;
import com.edu.icesi.bugtemplate.error.exception.UserDemoError;
import com.edu.icesi.bugtemplate.error.exception.UserDemoException;
import com.edu.icesi.bugtemplate.mapper.UserMapper;
import com.edu.icesi.bugtemplate.mapper.UserMapperImpl;
import com.edu.icesi.bugtemplate.service.UserService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class NotWorkingTests {

    private static final String DOMAIN = "@domain.com";
    private static final String USER_UUID = "5b666754-e217-4775-9c95-352ebb0673cb";

    private static final String PHONE_NUMBER = "+57";
    private UserController userController;

    private UserService userService;
    private UserMapper userMapper;

    @BeforeEach
    public void init(){
        userMapper = new UserMapperImpl();
        userService = mock(UserService.class);
        userController = new UserController(userService, userMapper);
    }

    // Implement a test for UserController that validates createUser has the correct parameters.
    @Test
    public void createUserWithValidParameters(){
        UserDTO userDTO = createCorrectUser();
        when(userService.createUser(any())).thenReturn(userMapper.fromDTO(userDTO));
        assertEquals(userDTO, userController.createUser(createCorrectUser()));
    }

    @Test
    public void userControllerCallsUserServiceWithCorrectParameters(){
        userController.createUser(createCorrectUser());
        verify(userService, times(1)).createUser(any());
    }

    private UserDTO createCorrectUser(){
        return UserDTO.builder().email("cuatro04sr@domain.com").phoneNumber("+573222050551").firstName("John").lastName("Doe").id(UUID.fromString(USER_UUID)).build();
    }
}
