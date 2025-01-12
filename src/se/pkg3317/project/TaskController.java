/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.pkg3317.project;

import java.awt.HeadlessException;
import javax.swing.JOptionPane;

/**
 *
 * @author No2Mo
 */
public class TaskController {

    private TaskView taskView;
    private addTaskView addTaskView;
    private SQLConnection sql;
    private TaskOperation taskOperation;
    private TaskOperation addTaskOperation;
    private TaskOperation updateTaskOperation;
    private TaskOperation deleteTaskOperation;
    private Category work;
    private Category holiday;
    private Category home;

    public TaskController(addTaskView addTaskView, SQLConnection sql) {
        this.addTaskView = addTaskView;
        this.sql = sql;

        addTaskOperation = new AddTaskOperation();
        updateTaskOperation = new UpdateTaskOperation();
        deleteTaskOperation = new DeleteTaskOperation();

    }

    private Category getCategoryByCategoryName(String categoryName) {

        if (categoryName.equals("Work")) {
            return work;
        } else if (categoryName.equals("Holiday")) {
            return holiday;
        } else {
            return home;
        }
    }

    private void executeAddTask() {
        try {

            Task task;

            String taskName = addTaskView.getTaskNameField().getText();
            String description = addTaskView.getDescriptionTextArea().getText();
            String deadline = addTaskView.getSelectedDay() + "-" + addTaskView.getSelectedMonth() + "-2025";
            String categoryName = addTaskView.getSelectedCategory();

            if (categoryName.equals("Work")) {
                task = new Task(taskName, description, work, deadline);
            } else if (categoryName.equals("Holiday")) {
                task = new Task(taskName, description, holiday, deadline);
            } else {
                task = new Task(taskName, description, home, deadline);
            }

            if (taskName.isEmpty() || description.isEmpty() || categoryName.isEmpty() || deadline.isEmpty()) {
                JOptionPane.showMessageDialog(addTaskView, "All fields are required!");
                return;
            }

            //adding the new task to sql table
            taskOperation = addTaskOperation;
            taskOperation.execute(task);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(addTaskView, "Error adding task: " + e.getMessage());
        }
    }

    public void executeDeleteTask() {
        try {
            int selectedRow = taskView.getTasklistTable().getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(taskView, "Please select a task to delete.");
                return;
            }

            String taskName = String.valueOf(taskView.getTasklistTable().getModel().getValueAt(selectedRow, 0));
            
            getCategoryByCategoryName(taskName);
            taskOperation = deleteTaskOperation;
            taskOperation.execute();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(taskView, "Error deleting task: " + e.getMessage());
        }

    }

    private void executeUpdateTask() {
        try {
            int selectedRow = taskView.getTasklistTable().getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(taskView, "Please select a task to edit.");
                return;
            }
            Task task;
            String taskName = addTaskView.getTaskNameField().getText();
            String description = addTaskView.getDescriptionTextArea().getText();
            String deadline = addTaskView.getSelectedDay() + "-" + addTaskView.getSelectedMonth() + "-2025";
            String categoryName = addTaskView.getSelectedCategory();

            if (taskName.isEmpty() || description.isEmpty() || categoryName.isEmpty() || deadline.isEmpty()) {
                JOptionPane.showMessageDialog(taskView, "All fields are required!");
                return;
            }

            if (categoryName.equals("Work")) {
                task = work.getTaskByName(taskName);
            } else if (categoryName.equals("Holiday")) {
                task = holiday.getTaskByName(taskName);
            } else {
                task = home.getTaskByName(taskName);
            }

            taskOperation = updateTaskOperation;
            taskOperation.execute(task);

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(taskView, "Error updating task: " + e.getMessage());
        }
    }

}
