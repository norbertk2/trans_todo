package com.trans.todos.api.v1.mapper;

import com.trans.todos.api.v1.domain.Task;
import com.trans.todos.api.v1.domain.User;
import com.trans.todos.api.v1.model.TaskDTO;
import com.trans.todos.api.v1.model.UserDTO;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class TaskMapperTest {

    public static final Long ID = 1L;
    public static final String DESCRIPTION = "Some task todo";
    public static final Long USER_ID = 1L;
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final LocalDate EXPIRE_DATE = LocalDate.now().plusDays(1);

    TaskMapper taskMapper = TaskMapper.INSTANCE;


    @Before
    public void setUp() throws Exception {
    }


    @Test
    public void taskToTaskDTOTest() {

        User user = User.builder().id(USER_ID).login(LOGIN).password(PASSWORD).build();

        Task task = Task.builder().id(ID).description(DESCRIPTION).user(user).expireDate(EXPIRE_DATE).build();

        TaskDTO taskDTO = taskMapper.taskToTaskDTO(task);

        assertEquals(ID, taskDTO.getId());
        assertEquals(DESCRIPTION, taskDTO.getDescription());
        assertEquals(LOGIN, taskDTO.getUser().getLogin());
        assertEquals(USER_ID, taskDTO.getUser().getId());
        assertEquals(EXPIRE_DATE, taskDTO.getExpireDate());

    }

    @Test
    public void taskDTOtoTaskTest() {

        UserDTO userDTO = new UserDTO();
        userDTO.setId(USER_ID);
        userDTO.setLogin(LOGIN);

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setDescription(DESCRIPTION);
        taskDTO.setId(ID);
        taskDTO.setExpireDate(EXPIRE_DATE);
        taskDTO.setUser(userDTO);


        Task task = taskMapper.taskDTOtoTask(taskDTO);

        assertEquals(ID, task.getId());
        assertEquals(DESCRIPTION, task.getDescription());
        assertEquals(EXPIRE_DATE, task.getExpireDate());
        assertEquals(LOGIN, task.getUser().getLogin());
        assertEquals(USER_ID, task.getUser().getId());

    }
}