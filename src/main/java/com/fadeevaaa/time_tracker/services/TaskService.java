package com.fadeevaaa.time_tracker.services;

import com.fadeevaaa.time_tracker.models.entities.Task;
import com.fadeevaaa.time_tracker.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasks(Long id) {
        return taskRepository.findByUser_Id(id);
    }
}
