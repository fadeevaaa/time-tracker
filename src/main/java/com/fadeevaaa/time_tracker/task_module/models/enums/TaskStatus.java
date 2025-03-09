package com.fadeevaaa.time_tracker.task_module.models.enums;

public enum TaskStatus {
    IN_PROCESS("В работе"),
    COMPLETED("Завершена");

    private final String RUS_STATUS;

    TaskStatus(String RUS_STATUS) {
        this.RUS_STATUS = RUS_STATUS;
    }

    public String getRUS_STATUS() {
        return RUS_STATUS;
    }
}

