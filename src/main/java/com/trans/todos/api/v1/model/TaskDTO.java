package com.trans.todos.api.v1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskDTO {

    private Long id;

    @JsonIgnore
    private UserDTO user;

    private LocalDate expireDate;
    private String title;
    private String description;
    private Boolean finished;
}
