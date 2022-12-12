package com.edu.icesi.bugtemplate.toFixTests;

import com.edu.icesi.bugtemplate.controller.UserController;
import com.edu.icesi.bugtemplate.dto.UserDTO;
import com.edu.icesi.bugtemplate.mapper.UserMapper;
import com.edu.icesi.bugtemplate.mapper.UserMapperImpl;
import com.edu.icesi.bugtemplate.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class NotWorkingTests {

    private static final String USER_UUID = "5b666754-e217-4775-9c95-352ebb0673cb";

    // Implement a test for UserController that validates createUser has the correct parameters.
    private UserController userController;

    private UserService userService;

    @BeforeEach
    public void init(){
        UserMapper userMapper = new UserMapperImpl();
        userService = mock(UserService.class);
        userController = new UserController(userService, userMapper);
    }

    @Test
    public void userControllerCallsUserServiceWithCorrectParameters(){
        userController.createUser(createDummyUser());
        verify(userService, times(1)).createUser(any());
    }

    private UserDTO createDummyUser(){
        return UserDTO.builder().email("email@domain.com").phoneNumber("+573153823657").firstName("John").lastName("Doe").id(UUID.fromString(USER_UUID)).build();
    }

}
