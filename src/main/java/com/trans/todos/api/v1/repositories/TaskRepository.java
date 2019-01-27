package com.trans.todos.api.v1.repositories;

import com.trans.todos.api.v1.domain.Task;
import com.trans.todos.api.v1.model.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUser(UserDTO userDTO);

    List<Task> findByUserAndExpireDate(UserDTO userDTO, LocalDate date);

    List<Task> findByUser_Id(Long id);

    List<Task> findByUser_IdAndExpireDate(Long id, LocalDate localDate);

    List<Task> findByUserLogin(String login);

    List<Task> findByUserLoginAndFinished(String login, Boolean finished);

    List<Task> findByExpireDateAndFinished(LocalDate date, Boolean finished);
}
