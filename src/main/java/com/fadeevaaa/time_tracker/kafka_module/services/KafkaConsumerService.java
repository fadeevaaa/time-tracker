package com.fadeevaaa.time_tracker.kafka_module.services;

import com.fadeevaaa.time_tracker.task_module.models.entities.Task;
import com.fadeevaaa.time_tracker.task_module.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    private final TaskService taskService;

    @Autowired
    public KafkaConsumerService(TaskService taskService) {
        this.taskService = taskService;
    }

    @KafkaListener(topics = "tasks", groupId = "tasks_changes", containerFactory = "kafkaListenerContainerFactory")
    public void listen(Task task) {
        System.out.println("Message received: " + task);
        taskService.saveTask(task, 2L);
    }
}
