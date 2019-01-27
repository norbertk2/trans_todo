package com.trans.todos.api.v1.config;

import com.trans.todos.api.v1.model.TaskDTO;
import com.trans.todos.api.v1.model.UserDTO;
import com.trans.todos.api.v1.services.EmailService;
import com.trans.todos.api.v1.services.TaskService;
import com.trans.todos.api.v1.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
public class ScheduledAction {

    private final EmailService emailService;
    private final TaskService taskService;
    private final UserService userService;

    public ScheduledAction(EmailService emailService, TaskService taskService, UserService userService) {
        this.emailService = emailService;
        this.taskService = taskService;
        this.userService = userService;
    }

    @Scheduled(cron = "0 0 7 * * ?")
    public void sendEmails() {
        log.info("sending...");

        List<UserDTO> userDTOS = userService.getUsers();

        for (UserDTO user : userDTOS) {

            List<TaskDTO> taskDTOS = taskService.getTaskByUserIdAndData(user.getId(), LocalDate.now().plusDays(1L));

            if (taskDTOS.size() > 0) {

                StringBuilder mailContent = new StringBuilder();

                for (TaskDTO taskDTO : taskDTOS) {
                    if (!taskDTO.getFinished()) {
                        mailContent.append(String.format("Task #%s: %s: %s \n\r", taskDTO.getId(), taskDTO.getTitle(), taskDTO.getDescription()));
                    }
                }
                emailService.sendEmail(user.getEmail(), "TASKS", mailContent.toString());
            }
        }
    }
}
