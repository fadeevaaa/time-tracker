package com.fadeevaaa.time_tracker.services;

import com.fadeevaaa.time_tracker.models.entities.*;
import com.fadeevaaa.time_tracker.models.enums.TaskStatus;
import com.fadeevaaa.time_tracker.models.enums.TaskType;
import com.fadeevaaa.time_tracker.repositories.TaskRepository;
import com.fadeevaaa.time_tracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasks(Long id) {
        return taskRepository.findByUser_Id(id);
    }

    public void saveTask(Task task, Long currentUserId) {
        Task newTask;
        if (task.getTaskType().equals(TaskType.EASY)) {
            newTask = new EasyTask(task.getTaskType(), task.getTitle(), task.getDescription());
        }
        else if (task.getTaskType().equals(TaskType.MID)) {
            newTask = new MidTask(task.getTaskType(), task.getTitle(), task.getDescription());
        }
        else {
            newTask = new HardTask(task.getTaskType(), task.getTitle(), task.getDescription());
        }
        Optional<User> optionalUser = userRepository.findById(currentUserId);
        newTask.setUser(optionalUser.isPresent() ? optionalUser.get() : null);
        taskRepository.save(newTask);
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).get();
    }

    public void editTask(Long id) {
        Task taskForUpdate = taskRepository.findById(id).get();
        taskForUpdate.setTaskStatus(TaskStatus.COMPLETED);
        taskRepository.save(taskForUpdate);
    }
}
