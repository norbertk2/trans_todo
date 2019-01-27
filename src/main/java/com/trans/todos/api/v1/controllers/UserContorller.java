package com.trans.todos.api.v1.controllers;


import com.trans.todos.api.v1.model.UserDTO;
import com.trans.todos.api.v1.model.UserListDTO;
import com.trans.todos.api.v1.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserContorller {

    private final UserService userService;

    public UserContorller(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    UserListDTO getUsers() {
        return new UserListDTO(userService.getUsers());
    }


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    UserDTO registerUser(@RequestBody UserDTO userDTO) {
        return userService.registerUser(userDTO);
    }

}
