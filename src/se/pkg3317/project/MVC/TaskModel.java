/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.pkg3317.project.MVC;

import java.util.ArrayList;
import java.util.Date;
import se.pkg3317.project.observer.TaskObserver;
import se.pkg3317.project.observer.TaskSubject;
import se.pkg3317.project.strategy.TaskOperation;
import se.pkg3317.project.tools.SQLConnection;
import se.pkg3317.project.tools.TimeOperations;

/**
 *
 * @author anil
 */
public class TaskModel implements TaskSubject {

    public final Category work;
    public final Category holiday;
    public final Category home;

    private SQLConnection sqlConnection;

    private Date anilinDG = TimeOperations.stringToDate("20.01.2025");
    private Date ozaninDG = TimeOperations.stringToDate("25.01.2025");

    private TaskOperation addTaskOperation;
    private TaskOperation updateTaskOperation;
    private TaskOperation deleteTaskOperation;

    private ArrayList<TaskObserver> observers = new ArrayList<>();

    public TaskModel(TaskOperation addTaskOperation, TaskOperation updateTaskOperation, TaskOperation deleteTaskOperation) {
        work = new Category("work");
        holiday = new Category("holiday");
        home = new Category("home");
        sqlConnection = new SQLConnection();
        sqlConnection.listTasksByCategory(work, home, holiday);
        this.addTaskOperation = addTaskOperation;
        this.updateTaskOperation = updateTaskOperation;
        this.deleteTaskOperation = deleteTaskOperation;
    }

    public Category getCategoryByCategoryName(String categoryName) {

        return switch (categoryName) {
            case "work" -> work;
            case "holiday" -> holiday;
            case "home" -> home;
            default -> null;
        };
    }

    public SQLConnection getsQLConnection() {
        return sqlConnection;
    }

    public Date getAnilinDG() {
        return anilinDG;
    }

    public Date getOzaninDG() {
        return ozaninDG;
    }

    private void executeTask(TaskOperation operation, Task task) {
        operation.execute(task);
        notifyObservers();
    }

    void handleAddTask(Task task) {
        executeTask(addTaskOperation, task);
    }

    void handleDeleteTask(Task deletedTask) {
        executeTask(deleteTaskOperation, deletedTask);
    }

    void handleUpdateTask(Task task) {
        executeTask(updateTaskOperation, task);
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

}
