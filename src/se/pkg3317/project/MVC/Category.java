/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.pkg3317.project.MVC;

import se.pkg3317.project.observer.TaskSubject;
import se.pkg3317.project.observer.TaskObserver;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import se.pkg3317.project.decorator.Message;

/**
 *
 * @author anil
 */
public class Category implements TaskSubject, Component {

    private String categoryName;
    private ArrayList<Task> taskList;
    private ArrayList<TaskObserver> observers = new ArrayList<>();

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
    public void addObserver(TaskObserver o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(TaskObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (TaskObserver o : observers) {
            o.update();
        }
    }

    @Override
    public void sendNotification() {
        for(Task task : taskList){
            task.sendNotification();
        }
    }

}
