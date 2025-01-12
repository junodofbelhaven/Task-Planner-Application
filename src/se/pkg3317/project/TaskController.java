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
    private Category work;
    private Category holiday;
    private Category home;

    public TaskController(addTaskView addTaskView, SQLConnection sql) {
        this.addTaskView = addTaskView;
        this.sql = sql;
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

            taskOperation = new AddTaskOperation();

            taskOperation.execute(sql, task);

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

            int taskID = (int) taskView.getTasklistTable().getModel().getValueAt(selectedRow, 0);

            taskOperation = new DeleteTaskOperation(taskID);
            taskOperation.execute(sql, null);
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
            int taskId = (int) taskView.getTasklistTable().getModel().getValueAt(selectedRow, 0);
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
                JOptionPane.showMessageDialog(taskView, "All fields are required!");
                return;
            }

           
            taskOperation = new UpdateTaskOperation();
            taskOperation.execute(sql, task);

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(taskView, "Error updating task: " + e.getMessage());
        }
    }

}
