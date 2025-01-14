/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.pkg3317.project.MVC;

import se.pkg3317.project.decorator.BasicMessage;
import se.pkg3317.project.decorator.Message;

/**
 *
 * @author anil
 */
public class Task implements Component {

    private String taskName;
    private String desc;
    private Category category;
    private String deadline;
    private Message message;

    public Task(String taskName, String desc, Category category, String deadline) {
        this.taskName = taskName;
        this.desc = desc;
        this.category = category;
        this.deadline = deadline;
        category.addTask(this);
        message = new BasicMessage();
    }

    public void updateTaskVariables(String desc, Category category, String deadline) {
        this.desc = desc;
        this.deadline = deadline;
        category.removeTask(this);
        this.category = category;
        category.addTask(this);
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDesc() {
        return desc;
    }

    public Category getCategory() {
        return category;
    }

    public String getDeadline() {
        return deadline;
    }

    @Override
    public void sendNotification() {

    }

}
