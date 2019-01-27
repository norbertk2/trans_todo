package com.trans.todos.api.v1.controllers;

import com.trans.todos.api.v1.model.UserDTO;
import com.trans.todos.api.v1.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static com.trans.todos.api.v1.controllers.AstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


public class UserContorllerTest {


    @Mock
    UserService userService;

    @InjectMocks
    UserContorller userContorller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userContorller).build();
    }

    @Test
    public void getUsers() throws Exception {

        List<UserDTO> userDTOS = Arrays.asList( new UserDTO(),new UserDTO(),new UserDTO() );

        when(userService.getUsers()).thenReturn(userDTOS);

        mockMvc.perform(get("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.users", hasSize(3)));

    }

    @Test
    public void registerUser() throws Exception {

        UserDTO userDTO = new UserDTO();
        userDTO.setLogin("test_login");
        userDTO.setEmail("test@test.com");

        UserDTO returnUserDTO = new UserDTO();
        returnUserDTO.setLogin(userDTO.getLogin());
        returnUserDTO.setEmail(userDTO.getEmail());

        when(userService.registerUser(userDTO)).thenReturn(returnUserDTO);

        mockMvc.perform(post("/api/v1/users/register")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.login", equalTo(userDTO.getLogin())))
                .andExpect(jsonPath("$.email", equalTo(userDTO.getEmail())));

    }
}