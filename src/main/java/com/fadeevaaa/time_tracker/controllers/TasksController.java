package com.fadeevaaa.time_tracker.controllers;

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

    @Autowired
    public TasksController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping()
    public String showTaskList(@RequestParam("id") Long id, Model model) {
        List<Task> tasks = taskService.getTasks(id);
        model.addAttribute("tasks", tasks);
        model.addAttribute("id", id);
        return "tasks/list";
    }
}
