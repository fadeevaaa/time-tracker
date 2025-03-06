package com.fadeevaaa.time_tracker.models.entities;

import jakarta.persistence.Entity;

@Entity
public class EasyTask extends Task {

    public EasyTask(int taskNumber, String type, String title, String description) {
        super(taskNumber, type, title, description);
    }

    public EasyTask() {
    }
}
