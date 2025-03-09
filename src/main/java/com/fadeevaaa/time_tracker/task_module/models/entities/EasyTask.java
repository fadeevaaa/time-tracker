package com.fadeevaaa.time_tracker.task_module.models.entities;

import com.fadeevaaa.time_tracker.task_module.models.enums.TaskType;
import jakarta.persistence.Entity;

@Entity
public class EasyTask extends Task {

    public EasyTask(TaskType taskType, String title, String description) {
        super(taskType, title, description);
    }

    public EasyTask() {
    }
}
