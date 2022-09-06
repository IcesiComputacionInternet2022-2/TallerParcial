package com.edu.icesi.bugtemplate.mapper;

import com.edu.icesi.bugtemplate.dto.UserDTO;
import com.edu.icesi.bugtemplate.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

     User fromDTO(UserDTO userDTO);
     UserDTO fromUser(User user);
}
