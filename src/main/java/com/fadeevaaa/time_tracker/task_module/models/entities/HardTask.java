package com.fadeevaaa.time_tracker.task_module.models.entities;

import com.fadeevaaa.time_tracker.task_module.models.enums.TaskType;
import jakarta.persistence.Entity;

@Entity
public class HardTask extends Task {

    public HardTask(TaskType taskType, String title, String description) {
        super(taskType, title, description);
    }

    public HardTask() {
    }
}
