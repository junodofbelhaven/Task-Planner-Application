/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.pkg3317.project;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

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
    public void addToTable(DefaultTableModel tableModel) {
        for(Task t : taskList){
            t.addToTable(tableModel);
        }
    }

    @Override
    public void removeFromTable(DefaultTableModel tableModel) {
        for(Task t : taskList){
            t.removeFromTable(tableModel);
        }
    }

}
