/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.pkg3317.project;

import java.util.ArrayList;

/**
 *
 * @author anil
 */
public class Category implements TaskSubject{
    
    private String categoryName;
    private ArrayList<Task> taskList;
    private ArrayList<TaskObserver> observers = new ArrayList<>();

    public Category(String categoryName, ArrayList<Task> taskList) {
        this.categoryName = categoryName;
        this.taskList = taskList;
    }

    public String getCategoryName() {
        return categoryName;
    }
    
    public void addTask(Task task){
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
        for(TaskObserver o : observers){
            o.update();
        }
    }
     
  
    
    
    
    
}
