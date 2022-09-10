package com.edu.icesi.bugtemplate.toFixTests;

import com.edu.icesi.bugtemplate.controller.UserController;
import com.edu.icesi.bugtemplate.dto.UserDTO;
import com.edu.icesi.bugtemplate.mapper.UserMapper;
import com.edu.icesi.bugtemplate.mapper.UserMapperImpl;
import com.edu.icesi.bugtemplate.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class NotWorkingTests {

    // Implement a test for UserController that validates createUser has the correct parameters.

    private UserController userController;
    private UserServiceImpl userService;
    private UserMapper userMapper;
    private UserDTO userForTests = new UserDTO(
            UUID.randomUUID(),
            "Giovanni@domain.com",
            "+573225034089",
            "Giovanni",
            "Mosquera"
    );

    @BeforeEach
    public void init() {
        userMapper = new UserMapperImpl();
        userService = mock(UserServiceImpl.class);
        userController = new UserController(userService, userMapper);
    }

    @Test
    public void userControllerCallsUserServiceWithCorrectParameters(){
        userController.createUser(userForTests);
        verify(userService, times(1)).createUser(userMapper.fromDTO(userForTests));
    }




}
