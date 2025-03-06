package com.fadeevaaa.time_tracker.models.entities;

import com.fadeevaaa.time_tracker.models.enums.TaskStatus;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "tasks")
public abstract class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "task_number")
    private int taskNumber;
    @Column(name = "type")
    private String type;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "comment")
    private String comment;
    @Column(name = "decision")
    private String decision;
    @Column(name = "spent_time")
    private int spentTime;
    @Column(name = "task_status")
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;
    @Column(name = "start_time")
    private Date startTime;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Task(int taskNumber, String type, String title, String description) {
        this.taskNumber = taskNumber;
        this.type = type;
        this.title = title;
        this.description = description;
        taskStatus = TaskStatus.CREATED;
        spentTime = 0;
    }

    public Task() {
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public Date getStartTime() {
        return startTime;
    }


    public int getSpentTime() {
        return spentTime;
    }

    public void setSpentTime(int spentTime) {
        this.spentTime = spentTime;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
//
//    public void workWithTask() {
//        if (status.equals("Создана") || status.equals("Отложена")) {
//            System.out.println("\nЗапущен отсчет времени по задаче.\n");
//            startTime = new Date();
//            status = TaskStatus.IN_PROCESS.getRUS_STATUS();
//        } else if (status.equals("Завершена")) {
//            System.out.println("\nНевозможно продолжить работу с завершенной задачей.\n");
//        }
//    }
//
//    public void completeTask(Scanner sc) {
//        System.out.println("\nУкажите решение по задаче: ");
//        decision = sc.nextLine();
//        System.out.println("\nЗадача завершена. Отсчет времени по задаче остановлен.\n");
//        Date complitionTime = new Date();
//        int spentTime = (int) (complitionTime.getTime() - startTime.getTime()) / 1000 / 60;
//        this.spentTime += spentTime;
//        status = TaskStatus.COMPLETED.getRUS_STATUS();
//    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskNumber=" + taskNumber +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", comment='" + comment + '\'' +
                ", decision='" + decision + '\'' +
                ", spentTime=" + spentTime +
                ", taskStatus=" + taskStatus +
                ", startTime=" + startTime +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
