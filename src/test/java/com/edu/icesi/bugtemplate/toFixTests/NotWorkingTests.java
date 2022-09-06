package com.edu.icesi.bugtemplate.toFixTests;

import com.edu.icesi.bugtemplate.controller.UserController;
import com.edu.icesi.bugtemplate.dto.UserDTO;
import com.edu.icesi.bugtemplate.mapper.UserMapper;
import com.edu.icesi.bugtemplate.mapper.UserMapperImpl;
import com.edu.icesi.bugtemplate.model.User;
import com.edu.icesi.bugtemplate.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;

public class NotWorkingTests {

    private static final String USER_UUID = "5b666754-e217-4775-9c95-352ebb0673cb";
    private static final String EMAIL = "belongTo@domain.com";
    private static final String PHONE_NUMBER = "+573001234567";

    // Implement a test for UserController that validates createUser has the correct parameters.
    private UserController userController;

    private UserService userService;

    private UserMapper userMapper;

    @BeforeEach
    public void init(){
        userMapper = new UserMapperImpl();
        userService = mock(UserService.class);
        userController = new UserController(userService, userMapper);
    }

    @Test
    public void userControllerCallsUserServiceWithCorrectParameters(){
        UserDTO userDTO = createDummyUser(EMAIL, PHONE_NUMBER);
        userController.createUser(userDTO);
        verify(userService, times(1)).createUser(userMapper.fromDTO(userDTO));
    }

    private UserDTO createDummyUser(String email, String phoneNumber){
        return UserDTO.builder().email(email).phoneNumber(phoneNumber).firstName("John").lastName("Doe").id(UUID.fromString(USER_UUID)).build();
    }
}
