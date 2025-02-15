package com.cdh.apilibreria.model.mappers;

import com.cdh.apilibreria.model.DTO.UserDTO;
import com.cdh.apilibreria.model.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDTOMapper {
    UserDTO toUserDTO(User user);
    User toUser(UserDTO userDTO);
}
