package com.edu.icesi.bugtemplate.mapper;

import com.edu.icesi.bugtemplate.dto.UserDTO;
import com.edu.icesi.bugtemplate.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
<<<<<<< Updated upstream
    date = "2022-09-06T14:48:10-0500",
=======
    date = "2022-09-12T13:41:55-0500",
>>>>>>> Stashed changes
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User fromDTO(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

<<<<<<< Updated upstream
        user.id( userDTO.getId() );
        user.email( userDTO.getEmail() );
        user.phoneNumber( userDTO.getPhoneNumber() );
        user.firstName( userDTO.getFirstName() );
        user.lastName( userDTO.getLastName() );

=======
>>>>>>> Stashed changes
        return user.build();
    }

    @Override
    public UserDTO fromUser(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO.UserDTOBuilder userDTO = UserDTO.builder();

        userDTO.id( user.getId() );
        userDTO.email( user.getEmail() );
        userDTO.phoneNumber( user.getPhoneNumber() );
        userDTO.firstName( user.getFirstName() );
        userDTO.lastName( user.getLastName() );

        return userDTO.build();
    }
}
