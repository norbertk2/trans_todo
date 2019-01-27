package com.trans.todos.api.v1.mapper;

import com.trans.todos.api.v1.domain.User;
import com.trans.todos.api.v1.model.UserDTO;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserMapperTest {

    public static final Long USER_ID = 1L;
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";

    UserMapper userMapper = UserMapper.INSTANCE;


    @Test
    public void userToUserDTOTest() {

        User user = User.builder().id(USER_ID).login(LOGIN).password(PASSWORD).build();

        UserDTO userDTO = userMapper.userToUserDTO(user);

        assertEquals(LOGIN, userDTO.getLogin());
        assertEquals(USER_ID, userDTO.getId());
    }

    @Test
    public void userDTOToUserTest() {

        UserDTO userDTO = new UserDTO();
        userDTO.setId(USER_ID);
        userDTO.setLogin(LOGIN);

        User user = userMapper.userDTOToUser(userDTO);

        assertEquals(LOGIN, user.getLogin());
        assertEquals(USER_ID, user.getId());

    }
}