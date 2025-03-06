package com.fadeevaaa.time_tracker.services.interfaces;

import com.fadeevaaa.time_tracker.models.entities.Task;
import com.fadeevaaa.time_tracker.models.enums.TaskStatus;
import java.util.Date;
import java.util.Scanner;

public interface Postponable {
    default void postpone(Task task, Scanner sc) {
        System.out.println("\nУкажите комментарий: ");
        task.setComment(sc.nextLine());
        System.out.println("\nЗадача отложена. Отсчет времени по задаче остановлен.\n");
        Date complitionTime = new Date();
        int spentTime = (int) (complitionTime.getTime() - task.getStartTime().getTime()) / 1000 / 60;
        task.setSpentTime(task.getSpentTime() + spentTime);
//        task.setStatus(TaskStatus.POSTPONED.getRUS_STATUS());
    }
}
