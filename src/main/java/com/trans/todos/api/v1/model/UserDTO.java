package com.trans.todos.api.v1.model;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String login;
    private String password;
    private String email;

}
