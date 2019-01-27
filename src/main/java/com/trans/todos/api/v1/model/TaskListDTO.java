package com.trans.todos.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TaskListDTO {

    private List<TaskDTO> taskDTOS;
}
