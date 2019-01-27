package com.trans.todos.api.v1.services;

import com.trans.todos.api.v1.model.TaskDTO;
import com.trans.todos.api.v1.model.UserDTO;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {

    List<TaskDTO> getTasks();

    TaskDTO getTaskById(Long id);

    List<TaskDTO> getTasksByUserDTO(UserDTO userDTO);

    List<TaskDTO> getTasksByUserDTOAndData(UserDTO userDTO, LocalDate date);

    TaskDTO createTask(TaskDTO taskDTO);

    TaskDTO updateTask(Long id, TaskDTO taskDTO);

    void deleteTask(TaskDTO taskDTO);

    List<TaskDTO> getTasksByUserId(Long id);

    List<TaskDTO> getTaskByUserIdAndData(Long id, LocalDate date);

    List<TaskDTO> getTasksByUserName(String username);

    List<TaskDTO> getFinishedTasksByUserName(String username);

    List<TaskDTO> getUnfinishedTasksByUserName(String username);

    List<TaskDTO> getUnfinishedTasksAndData(LocalDate date);
}
