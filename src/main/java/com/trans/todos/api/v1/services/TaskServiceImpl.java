package com.trans.todos.api.v1.services;

import com.trans.todos.api.v1.mapper.TaskMapper;
import com.trans.todos.api.v1.model.TaskDTO;
import com.trans.todos.api.v1.model.UserDTO;
import com.trans.todos.api.v1.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskDTO createTask(TaskDTO taskDTO) {
        return taskMapper.taskToTaskDTO(taskRepository.save(taskMapper.taskDTOtoTask(taskDTO)));
    }

    @Override
    public TaskDTO updateTask(Long id, TaskDTO taskDTO) {
        taskDTO.setId(id);
        return taskMapper.taskToTaskDTO(taskRepository.save(taskMapper.taskDTOtoTask(taskDTO)));
    }

    @Override
    public List<TaskDTO> getTasks() {
        return taskRepository
                .findAll()
                .stream()
                .map(task -> taskMapper.taskToTaskDTO(task))
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO getTaskById(Long id) {
        return taskRepository
                .findById(id)
                .map(task -> taskMapper.taskToTaskDTO(task))
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public List<TaskDTO> getTasksByUserDTO(UserDTO userDTO) {
        return taskRepository
                .findByUser(userDTO)
                .stream()
                .map(task -> taskMapper.taskToTaskDTO(task))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> getTasksByUserDTOAndData(UserDTO userDTO, LocalDate date) {
        return taskRepository
                .findByUserAndExpireDate(userDTO, date)
                .stream()
                .map( task -> taskMapper.taskToTaskDTO(task))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTask(TaskDTO taskDTO) {
        taskRepository.delete(taskMapper.taskDTOtoTask(taskDTO));
    }

    @Override
    public List<TaskDTO> getTasksByUserId(Long id) {
        return taskRepository
                .findByUser_Id(id)
                .stream()
                .map(task -> taskMapper.taskToTaskDTO(task))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> getTaskByUserIdAndData(Long id, LocalDate date) {
        return taskRepository
                .findByUser_IdAndExpireDate(id,date)
                .stream()
                .map(task -> taskMapper.taskToTaskDTO(task))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> getTasksByUserName(String username) {
        return taskRepository
                .findByUserLogin(username)
                .stream()
                .map(task -> taskMapper.taskToTaskDTO(task))
                .collect(Collectors.toList());

    }

    @Override
    public List<TaskDTO> getFinishedTasksByUserName(String username) {
        return taskRepository
                .findByUserLoginAndFinished(username, false)
                .stream()
                .map(task -> taskMapper.taskToTaskDTO(task))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> getUnfinishedTasksByUserName(String username) {
        return taskRepository
                .findByUserLoginAndFinished(username, true)
                .stream()
                .map(task -> taskMapper.taskToTaskDTO(task))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> getUnfinishedTasksAndData(LocalDate date) {
        return taskRepository
                .findByExpireDateAndFinished(date, false)
                .stream()
                .map(task -> taskMapper.taskToTaskDTO(task))
                .collect(Collectors.toList());
    }
}
