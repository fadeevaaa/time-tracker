package com.fadeevaaa.time_tracker.task_module.controllers;

import com.fadeevaaa.time_tracker.kafka_module.services.KafkaProducerService;
import com.fadeevaaa.time_tracker.task_module.models.entities.EasyTask;
import com.fadeevaaa.time_tracker.task_module.models.entities.Task;
import com.fadeevaaa.time_tracker.task_module.services.TaskService;
import com.fadeevaaa.time_tracker.user_module.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TasksController {

    private final TaskService taskService;
    private final UserService userService;

    @Autowired
    public TasksController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping()
    public String showTaskList(Model model) {
        List<Task> tasks = taskService.getTasks(userService.getCurrentUser().getId());
        model.addAttribute("tasks", tasks);
        System.out.println(userService.getCurrentUser().getId());
        return "tasks/list";
    }

    @GetMapping("/new")
    public String newTask(@ModelAttribute("task") EasyTask task) {
        return "tasks/new";
    }

    @GetMapping("/{id}")
    public String chooseTask(@PathVariable("id") Long id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        model.addAttribute("currentUserId", userService.getCurrentUser().getId());
        return "tasks/task_details";
    }

    @GetMapping("/{id}/edit")
    public String closeTask(@PathVariable("id") Long id, Model model) {
        taskService.editTask(id);
        List<Task> tasks = taskService.getTasks(userService.getCurrentUser().getId());
        model.addAttribute("tasks", tasks);
        return "tasks/list";
    }
}
