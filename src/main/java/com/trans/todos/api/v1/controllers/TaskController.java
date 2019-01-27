package com.trans.todos.api.v1.controllers;

import com.trans.todos.api.v1.model.TaskDTO;
import com.trans.todos.api.v1.model.TaskListDTO;
import com.trans.todos.api.v1.model.UserDTO;
import com.trans.todos.api.v1.services.TaskService;
import com.trans.todos.api.v1.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    TaskListDTO getTasks(@AuthenticationPrincipal User user) {
        return new TaskListDTO(taskService.getTasksByUserName(user.getUsername()));
    }

    @GetMapping("/todo")
    @ResponseStatus(HttpStatus.OK)
    TaskListDTO getFinishedTasks(@AuthenticationPrincipal User user) {
        return new TaskListDTO(taskService.getFinishedTasksByUserName(user.getUsername()));
    }

    @GetMapping("/done")
    @ResponseStatus(HttpStatus.OK)
    TaskListDTO getUnfinishedTasks(@AuthenticationPrincipal User user) {
        return new TaskListDTO(taskService.getUnfinishedTasksByUserName(user.getUsername()));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    TaskDTO updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO, @AuthenticationPrincipal User user) {
        TaskDTO foundTaskDTO = taskService.getTaskById(id);
        if(foundTaskDTO != null && foundTaskDTO.getUser().getLogin().equals(user.getUsername())) {
            taskDTO.setId(id);
            taskDTO.setUser(userService.findByName(user.getUsername()));
            return taskService.updateTask(id, taskDTO);
        }
        else
            return null;
    }

//    @GetMapping("/user/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    TaskListDTO getTasksByUserId(@PathVariable Long id) {
//        return new TaskListDTO(taskService.getTasksByUserId(id));
//    }

//    @GetMapping("/user/{id}/date/{date}")
//    @ResponseStatus(HttpStatus.OK)
//    TaskListDTO getTasksByUserIdAndDate(@PathVariable Long id, @PathVariable String date) {
//        return new TaskListDTO(taskService.getTaskByUserIdAndData(id, LocalDate.parse(date)));
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    TaskDTO createTask(@RequestBody TaskDTO taskDTO, @AuthenticationPrincipal User user) {
        UserDTO userDTO = userService.findByName(user.getUsername());
        taskDTO.setUser(userDTO);
        return taskService.createTask(taskDTO);
    }

    @PostMapping("/create/")
    @ResponseStatus(HttpStatus.CREATED)
    TaskDTO createTaskByUserId(@RequestBody TaskDTO taskDTO, @AuthenticationPrincipal User user) {
        UserDTO userDTO = userService.findByName(user.getUsername());
        taskDTO.setUser(userDTO);
        taskDTO.setFinished(false);
        return taskService.createTask(taskDTO);
    }


}
