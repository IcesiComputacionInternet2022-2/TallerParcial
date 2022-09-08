package com.edu.icesi.bugtemplate.api;

import com.edu.icesi.bugtemplate.dto.UserDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RequestMapping("/users")
public interface UserAPI {

    @GetMapping("/{userId}")
    public UserDTO getUser(@PathVariable UUID userId);

    @PostMapping()
    public UserDTO createUser(@RequestBody UserDTO userDTO);

    @GetMapping
    public List<UserDTO> getUsers();

}
