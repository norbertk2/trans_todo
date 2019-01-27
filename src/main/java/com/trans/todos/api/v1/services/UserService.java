package com.trans.todos.api.v1.services;

import com.trans.todos.api.v1.model.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getUsers();

    UserDTO registerUser(UserDTO userDTO);

    UserDTO getUserById(Long id);

    UserDTO findByName(String login);
}
