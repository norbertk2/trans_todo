package com.trans.todos.api.v1.services;

import com.trans.todos.api.v1.domain.Task;
import com.trans.todos.api.v1.domain.User;
import com.trans.todos.api.v1.mapper.TaskMapper;
import com.trans.todos.api.v1.model.TaskDTO;
import com.trans.todos.api.v1.model.UserDTO;
import com.trans.todos.api.v1.repositories.TaskRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class TaskServiceTest {

    public static final Long ID = 1L;
    public static final String DESCRIPTION = "Some task";
    public static final LocalDate EXPIRE_DATE = LocalDate.now().plusDays(7);
    public static final Long USER_ID = 1L;
    public static final String LOGIN = "test login";
    public static final String PASSWORD = "test password";
    @Mock
    TaskRepository taskRepository;

    TaskService taskService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        taskService = new TaskServiceImpl(taskRepository, TaskMapper.INSTANCE);
    }

    @Test
    public void getTasks() {

        //given
        List<Task> tasks = Arrays.asList(new Task(), new Task(), new Task());
        when(taskRepository.findAll()).thenReturn(tasks);

        //when
        List<TaskDTO> taskDTOS = taskService.getTasks();

        //then
        assertEquals(tasks.size(), taskDTOS.size());


    }

    @Test
    public void getTaskById() {
        //given
        Task task = Task.builder().id(ID).description(DESCRIPTION).expireDate(EXPIRE_DATE).build();
        Optional<Task> optionalTask = Optional.of(task);

        when(taskRepository.findById(anyLong())).thenReturn(optionalTask);

        //when
        TaskDTO taskDTO = taskService.getTaskById(ID);

        //then
        assertEquals(ID, taskDTO.getId());
        assertEquals(DESCRIPTION, taskDTO.getDescription());
        assertEquals(EXPIRE_DATE, taskDTO.getExpireDate());

    }

    @Test
    public void getTasksByUserDTO() {
        //given
        UserDTO userDTO = new UserDTO();

//        Task task = Task.builder().id(ID).description(DESCRIPTION).expireDate(EXPIRE_DATE).build();
        List<Task> tasks = Arrays.asList(new Task(), new Task(), new Task());

        when(taskRepository.findByUser(any(UserDTO.class))).thenReturn(tasks);

        //when
        List<TaskDTO> taskDTOS = taskService.getTasksByUserDTO(userDTO);

        //then
        assertEquals(tasks.size(), taskDTOS.size());

    }

    @Test
    public void getTasksByUserDTOAndData() {
        //given
        UserDTO userDTO = new UserDTO();
        LocalDate searchDate = LocalDate.now();
        List<Task> tasks = Arrays.asList(new Task(), new Task(), new Task());

        when(taskRepository.findByUserAndExpireDate(any(UserDTO.class), any(LocalDate.class))).thenReturn(tasks);

        //when
        List<TaskDTO> taskDTOS = taskService.getTasksByUserDTOAndData(userDTO, searchDate);

        //then
        assertEquals(tasks.size(), taskDTOS.size());
    }

    @Test
    public void createTask() {
        //given
        UserDTO userDTO = new UserDTO();
        userDTO.setId(USER_ID);
        userDTO.setLogin(LOGIN);

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(ID);
        taskDTO.setExpireDate(EXPIRE_DATE);
        taskDTO.setUser(userDTO);

        Task savedTask = Task.builder().id(ID).description(DESCRIPTION).expireDate(EXPIRE_DATE).user(User.builder().id(USER_ID).build()).build();

        when(taskRepository.save(any(Task.class))).thenReturn(savedTask);

        //when
        TaskDTO savedTaskDTO = taskService.createTask(taskDTO);

        //then
        assertEquals(ID, savedTaskDTO.getId());
        assertEquals(DESCRIPTION, savedTaskDTO.getDescription());
        assertEquals(EXPIRE_DATE, savedTaskDTO.getExpireDate());
        assertEquals(USER_ID, savedTaskDTO.getUser().getId());
    }

    @Test
    public void updateTask() {

        //given
        UserDTO userDTO = new UserDTO();
        userDTO.setId(USER_ID);
        userDTO.setLogin(LOGIN);

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(ID);
        taskDTO.setExpireDate(EXPIRE_DATE);
        taskDTO.setUser(userDTO);

        Task updatedTask = Task.builder().id(ID).description(DESCRIPTION).expireDate(EXPIRE_DATE).user(User.builder().id(USER_ID).build()).build();

        when(taskRepository.save(any(Task.class))).thenReturn(updatedTask);

        //when
        TaskDTO updatedTaskDTO = taskService.updateTask(ID, taskDTO);

        //then
        assertEquals(ID, updatedTaskDTO.getId());
        assertEquals(DESCRIPTION, updatedTaskDTO.getDescription());
        assertEquals(EXPIRE_DATE, updatedTaskDTO.getExpireDate());
        assertEquals(USER_ID, updatedTaskDTO.getUser().getId());
    }
}