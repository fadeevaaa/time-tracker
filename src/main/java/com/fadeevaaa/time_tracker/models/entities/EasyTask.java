package com.fadeevaaa.time_tracker.models.entities;

import com.fadeevaaa.time_tracker.models.enums.TaskType;
import jakarta.persistence.Entity;

@Entity
public class EasyTask extends Task {

    public EasyTask(TaskType taskType, String title, String description) {
        super(taskType, title, description);
    }

    public EasyTask() {
    }
}
