package com.fadeevaaa.time_tracker.models.enums;

public enum TaskType {
    EASY("Легкая"),
    MID("Средняя"),
    HARD("Сложная");

    private final String RUS_TYPE;

    TaskType(String RUS_STATUS) {
        this.RUS_TYPE = RUS_STATUS;
    }

    public String getRUS_TYPE() {
        return RUS_TYPE;
    }
}
