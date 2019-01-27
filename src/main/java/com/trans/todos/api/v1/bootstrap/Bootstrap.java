package com.trans.todos.api.v1.bootstrap;

import com.trans.todos.api.v1.domain.Task;
import com.trans.todos.api.v1.domain.User;
import com.trans.todos.api.v1.repositories.TaskRepository;
import com.trans.todos.api.v1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Bootstrap implements CommandLineRunner {

    private UserRepository userRepository;
    private TaskRepository taskRepository;

    public Bootstrap(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User user1 = User.builder().id(1L).login("user1").password(passwordEncoder.encode("pass1")).email("shingeru@gmail.com").build();
        userRepository.save(user1);
        User user2 = User.builder().id(2L).login("user2").password(passwordEncoder.encode("pass2")).email("temp1@mali.com").build();
        userRepository.save(user2);
        System.out.println("Added users:" + userRepository.findAll().size());


        taskRepository.save(Task.builder().id(1L).description("New task 1").expireDate(LocalDate.now().plusDays(1L)).user(user1).finished(false).build());
        taskRepository.save(Task.builder().id(2L).description("New task 2").expireDate(LocalDate.now().plusDays(1L)).user(user1).finished(false).build());
        taskRepository.save(Task.builder().id(3L).description("New task 3").expireDate(LocalDate.now().plusDays(1L)).user(user2).finished(false).build());
        taskRepository.save(Task.builder().id(4L).description("New task 4").expireDate(LocalDate.now().plusDays(1L)).user(user2).finished(false).build());
        System.out.println("Added tasks: " + taskRepository.findAll().size());

    }
}
