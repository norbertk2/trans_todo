package com.trans.todos.api.v1.mapper;

import com.trans.todos.api.v1.domain.Task;
import com.trans.todos.api.v1.model.TaskDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    TaskDTO taskToTaskDTO(Task task);
    Task taskDTOtoTask(TaskDTO taskDTO);
}
