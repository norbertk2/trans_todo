package com.trans.todos.api.v1.services;

import com.trans.todos.api.v1.domain.User;
import com.trans.todos.api.v1.mapper.UserMapper;
import com.trans.todos.api.v1.model.UserDTO;
import com.trans.todos.api.v1.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    public static final Long ID = 1L;
    public static final String LOGIN = "login_test";
    public static final String PASSWORD = "password_test";

    @Mock
    UserRepository userRepository;

    UserService userService;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        userService = new UserServiceImpl(userRepository, UserMapper.INSTANCE);
    }

    @Test
    public void getUsers() {
        //given
        List<User> users = Arrays.asList(new User(), new User(), new User());
        when(userRepository.findAll()).thenReturn(users);

        //when
        List<UserDTO> userDTOS = userService.getUsers();

        //then
        assertEquals(users.size(), userDTOS.size());
    }

    @Test
    public void registerUser() {

        //given
        UserDTO userDTO = new UserDTO();
        userDTO.setId(ID);
        userDTO.setLogin(LOGIN);
        userDTO.setPassword(PASSWORD);

        User createdUser = User.builder()
                .id(userDTO.getId())
                .login(userDTO.getLogin())
                .password(userDTO.getPassword())
                .build();

        when(userRepository.save(any(User.class))).thenReturn(createdUser);

        //when
        UserDTO createdUserDTO = userService.registerUser(userDTO);

        //then
        assertEquals(ID, createdUserDTO.getId());
        assertEquals(LOGIN, createdUserDTO.getLogin());
        assertEquals(PASSWORD, createdUserDTO.getPassword());
    }
}