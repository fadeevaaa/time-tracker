package com.fadeevaaa.time_tracker.controllers;

import com.fadeevaaa.time_tracker.models.entities.EasyTask;
import com.fadeevaaa.time_tracker.models.entities.Task;
import com.fadeevaaa.time_tracker.models.entities.User;
import com.fadeevaaa.time_tracker.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TasksController {

    private final TaskService taskService;
    private Long currentUserId;

    @Autowired
    public TasksController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping()
    public String showTaskList(@RequestParam(value = "id") Long id, Model model) {
        List<Task> tasks = taskService.getTasks(id);
        model.addAttribute("tasks", tasks);
        currentUserId = id;
        return "tasks/list";
    }

    @GetMapping("/new")
    public String newTask(@ModelAttribute("task") EasyTask task) {
        return "tasks/new";
    }

    @PostMapping()
    public String createNewTask(@ModelAttribute("task") EasyTask task, Model model) {
        taskService.saveTask(task, currentUserId);
        List<Task> tasks = taskService.getTasks(currentUserId);
        model.addAttribute("tasks", tasks);
        return "tasks/list";
    }

    @GetMapping("/{id}")
    public String chooseTask(@PathVariable("id") Long id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        model.addAttribute("currentUserId", currentUserId);
        return "tasks/task_details";
    }

    @GetMapping("/{id}/edit")
    public String closeTask(@PathVariable("id") Long id, Model model) {
        taskService.editTask(id);
        List<Task> tasks = taskService.getTasks(currentUserId);
        model.addAttribute("tasks", tasks);
        return "tasks/list";
    }
}
