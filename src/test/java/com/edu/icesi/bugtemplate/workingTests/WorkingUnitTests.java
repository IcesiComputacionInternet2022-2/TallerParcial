package com.edu.icesi.bugtemplate.workingTests;


import com.edu.icesi.bugtemplate.constant.ErrorConstants;
import com.edu.icesi.bugtemplate.controller.UserController;
import com.edu.icesi.bugtemplate.dto.UserDTO;
import com.edu.icesi.bugtemplate.error.exception.UserDemoError;
import com.edu.icesi.bugtemplate.error.exception.UserDemoException;
import com.edu.icesi.bugtemplate.mapper.UserMapper;
import com.edu.icesi.bugtemplate.mapper.UserMapperImpl;
import com.edu.icesi.bugtemplate.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;

public class WorkingUnitTests {

    private static final String DOMAIN = "@domain.com";
    private static final String USER_UUID = "5b666754-e217-4775-9c95-352ebb0673cb";

    private static final String PHONE_NUMBER = "+57";

    private UserController userController;

    private UserService userService;

    @BeforeEach
    public void init(){
        UserMapper userMapper = new UserMapperImpl();
        userService = mock(UserService.class);
        userController = new UserController(userService, userMapper);
    }

    @Test
    public void emailShouldBelongToDomain(){
        try {
            userController.createUser(createDummyUser("notBelongTo@notDomain.com", "+573001234567"));
            fail();
        }
        catch (UserDemoException e){
            assertEquals(HttpStatus.BAD_REQUEST, e.getHttpStatus());
            assertNotNull(e.getError());
            UserDemoError userDemoError = e.getError();
            assertEquals(ErrorConstants.CODE_UD_01.getMessage(), userDemoError.getMessage());
            assertEquals(ErrorConstants.CODE_UD_01, userDemoError.getCode());
        }
    }

    @Test
    public void phoneNumberShouldBelongToColombia(){
        try {
            userController.createUser(createDummyUser("belongTo@domain.com", "+583001234567"));
            fail();
        }
        catch (UserDemoException e){
            assertEquals(HttpStatus.BAD_REQUEST, e.getHttpStatus());
            assertNotNull(e.getError());
            UserDemoError userDemoError = e.getError();
            assertEquals(ErrorConstants.CODE_UD_02.getMessage(), userDemoError.getMessage());
            assertEquals(ErrorConstants.CODE_UD_02, userDemoError.getCode());
        }
    }

    private UserDTO createDummyUser(String email, String phoneNumber){
        return UserDTO.builder().email(email).phoneNumber(phoneNumber).firstName("John").lastName("Doe").id(UUID.fromString(USER_UUID)).build();
    }

}
