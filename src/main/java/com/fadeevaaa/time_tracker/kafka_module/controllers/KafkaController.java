package com.fadeevaaa.time_tracker.kafka_module.controllers;

import com.fadeevaaa.time_tracker.kafka_module.services.KafkaProducerService;
import com.fadeevaaa.time_tracker.task_module.models.entities.EasyTask;
import com.fadeevaaa.time_tracker.user_module.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/tasks")
public class KafkaController {

    private final UserService userService;
    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public KafkaController(KafkaProducerService kafkaProducer, UserService userService) {
        this.kafkaProducerService = kafkaProducer;
        this.userService = userService;
    }

    @PostMapping()
    public RedirectView createNewTask(@ModelAttribute("task") EasyTask task, Model model) {
        kafkaProducerService.sendMessage(task);
        return new RedirectView("/tasks");
    }
}
