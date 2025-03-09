package com.fadeevaaa.time_tracker.kafka_module.services;

import com.fadeevaaa.time_tracker.task_module.models.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, Task> kafkaTemplate;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, Task> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Task task) {
        kafkaTemplate.send("tasks", task);
    }
}
