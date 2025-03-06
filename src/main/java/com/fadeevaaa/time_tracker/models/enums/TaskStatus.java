package com.fadeevaaa.time_tracker.models.enums;

public enum TaskStatus {
    CREATED("Создана"),
    IN_PROCESS("В работе"),
    POSTPONED("Отложена"),
    COMPLETED("Завершена");

    private final String RUS_STATUS;

    TaskStatus(String RUS_STATUS) {
        this.RUS_STATUS = RUS_STATUS;
    }

    public String getRUS_STATUS() {
        return RUS_STATUS;
    }
}

