package com.trans.todos.api.v1.mapper;

import com.trans.todos.api.v1.domain.User;
import com.trans.todos.api.v1.model.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userToUserDTO(User user);
    User userDTOToUser(UserDTO userDTO);

}
