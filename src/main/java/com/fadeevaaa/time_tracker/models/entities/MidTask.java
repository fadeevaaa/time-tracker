package com.fadeevaaa.time_tracker.models.entities;

import com.fadeevaaa.time_tracker.services.interfaces.Postponable;
import jakarta.persistence.Entity;

@Entity
public class MidTask extends Task implements Postponable {

    public MidTask(int taskNumber, String type, String title, String description) {
        super(taskNumber, type, title, description);
    }

    public MidTask() {
    }
}
