/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.pkg3317.project.MVC;

import java.util.ArrayList;


/**
 *
 * @author anil
 */
public class Category implements Component {

    private String categoryName;
    public ArrayList<Task> taskList;

    public Category(String categoryName) {
        this.categoryName = categoryName;
        this.taskList = new ArrayList<>();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void removeTask(Task task) {
        taskList.remove(task);
    }

    public Task getTaskByName(String taskName) {
        for (Task task : taskList) {
            if (task.getTaskName().equals(taskName)) {
                return task;
            }
        }
        return null;
    }    

    @Override
    public void sendNotification() {
        for(Task task : taskList){
            task.sendNotification();
        }
    }

}
