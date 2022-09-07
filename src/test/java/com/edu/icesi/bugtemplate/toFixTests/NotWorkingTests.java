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

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class NotWorkingTests {

    // Implement a test for UserController that validates createUser has the correct parameters.
    private UserController userController;

    private UserService userService;

    @BeforeEach
    public void init(){
        UserMapper userMapper = new UserMapperImpl();
        userService = mock(UserService.class);
        userController = new UserController(userService, userMapper);
    }
    private String id, email, phoneNumber, lastName, fistName;
    public void setupScenary1(){
        id = "810418ac-c7ce-466b-9636-b5c17a846d58";
        email ="nicolas@domain.com";
        phoneNumber="+573166017115";
        fistName="Nicolas";
        lastName="Penagos";

    }

    @Test
    public void userControllerCallsUserServiceWithCorrectParameters(){
        setupScenary1();
        UserDTO userDTO = new UserDTO(UUID.fromString(id), email, phoneNumber, fistName,lastName);
        User user  = new User(UUID.fromString(id), email, phoneNumber, fistName,lastName);
        userController.createUser(userDTO);
        verify(userService, times(1)).createUser(user);
    }

}
