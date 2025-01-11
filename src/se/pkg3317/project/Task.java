/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.pkg3317.project;

import java.util.Date;

/**
 *
 * @author anil
 */
public class Task {
    
    private String taskName;
    private String desc;
    private Category category;
    private String deadline;

    public Task(String taskName, String desc, Category category, String deadline) {
        this.taskName = taskName;
        this.desc = desc;
        this.category = category;
        this.deadline = deadline;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    
    
    
    
}
