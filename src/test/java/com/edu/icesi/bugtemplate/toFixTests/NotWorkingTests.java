package com.edu.icesi.bugtemplate.toFixTests;

import com.edu.icesi.bugtemplate.controller.UserController;
import com.edu.icesi.bugtemplate.dto.UserDTO;
import com.edu.icesi.bugtemplate.error.exception.UserDemoException;
import com.edu.icesi.bugtemplate.mapper.UserMapper;
import com.edu.icesi.bugtemplate.mapper.UserMapperImpl;
import com.edu.icesi.bugtemplate.model.User;
import com.edu.icesi.bugtemplate.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class NotWorkingTests {

    // Implement a test for UserController that validates createUser has the correct parameters.

    private static final String DOMAIN = "@domain.com";
    private static final String USER_UUID = "5b666754-e217-4775-9c95-352ebb0673cb";

    private static final String PHONE_NUMBER = "+57";

    UserController userController;
    UserService userService;
    UserMapper userMapper;

    @BeforeEach
    public void init(){
        userService = mock(UserService.class);
        userMapper = new UserMapperImpl();
        userController = new UserController(userService, userMapper);
    }

    @Test
    public void userControllerCallsUserServiceWithCorrectParameters(){
        try {
            UserDTO userDTO = createDummyUser("belongTo@domain.com", "+573001234567");
            when(userService.createUser(userMapper.fromDTO(userDTO))).thenReturn(new User(UUID.fromString(USER_UUID), "belongTo@domain.com", "+573001234567", "John", "Doe"));
            userService.createUser(userMapper.fromDTO(userDTO));
            verify(userService, times(1)).createUser(userMapper.fromDTO(userDTO));
        }catch (UserDemoException e){
            fail();
        }
    }

    private UserDTO createDummyUser(String email, String phoneNumber){
        return UserDTO.builder().email(email).phoneNumber(phoneNumber).firstName("John").lastName("Doe").id(UUID.fromString(USER_UUID)).build();
    }

}
