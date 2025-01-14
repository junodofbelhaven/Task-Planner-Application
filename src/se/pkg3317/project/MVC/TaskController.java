/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.pkg3317.project.MVC;

import se.pkg3317.project.MVC.Task;
import se.pkg3317.project.MVC.Category;
import se.pkg3317.project.strategy.AddTaskOperation;
import se.pkg3317.project.strategy.TaskOperation;
import se.pkg3317.project.strategy.DeleteTaskOperation;
import se.pkg3317.project.strategy.UpdateTaskOperation;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import se.pkg3317.project.tools.SQLConnection;
import se.pkg3317.project.MVC.TaskView;
import se.pkg3317.project.tools.addTaskView;

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

        switch (categoryName) {
            case "Work":
                return work;
            case "Holiday":
                return holiday;
            case "Home":
                return home;
            default:
                JOptionPane.showConfirmDialog(taskView, "invalid category.");
                break;
        }
        return null;

    }

    private void executeAddTask() {

        Task task;

        String taskName = addTaskView.getTaskNameField().getText();
        String description = addTaskView.getDescriptionTextArea().getText();
        String deadline = addTaskView.getSelectedDay() + "-" + addTaskView.getSelectedMonth() + "-2025";
        String categoryName = addTaskView.getSelectedCategory();

        if (taskName.isEmpty() || description.isEmpty() || categoryName.isEmpty() || deadline.isEmpty()) {
            JOptionPane.showMessageDialog(addTaskView, "All fields are required!");
            return;
        }

        task = new Task(taskName, description, getCategoryByCategoryName(categoryName), deadline);

        //adding the new task to sql table
        taskOperation = addTaskOperation;
        taskOperation.execute(task);

    }

    public void executeDeleteTask() {

        int selectedRow = taskView.getTasklistTable().getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(taskView, "Please select a task to delete.");
            return;
        }

        String deletedTaskName = String.valueOf(taskView.getTasklistTable().getModel().getValueAt(selectedRow, 0));
        String categoryName = String.valueOf(taskView.getTasklistTable().getModel().getValueAt(selectedRow, 2));

        Category category = getCategoryByCategoryName(categoryName);
        Task deletedTask = category.getTaskByName(deletedTaskName);

        category.removeTask(deletedTask);

        taskOperation = deleteTaskOperation;
        taskOperation.execute(deletedTask);

    }

    private void executeUpdateTask() {

        int selectedRow = taskView.getTasklistTable().getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(taskView, "Please select a task to edit.");
            return;
        }

        String taskName = String.valueOf(taskView.getTasklistTable().getModel().getValueAt(selectedRow, 0));
        String categoryName = String.valueOf(taskView.getTasklistTable().getModel().getValueAt(selectedRow, 2));
        Category category = getCategoryByCategoryName(categoryName);
        Task task = category.getTaskByName(taskName);
        
        String newDescription = addTaskView.getDescriptionTextArea().getText();
        String newDeadline = addTaskView.getSelectedDay() + "-" + addTaskView.getSelectedMonth() + "-2025";
        String newCategory = addTaskView.getSelectedCategory();

        if (newDescription.isEmpty() || newCategory.isEmpty() || newDeadline.isEmpty()) {
            JOptionPane.showMessageDialog(taskView, "All fields are required!");
            return;
        }

        
        task.updateTaskVariables(newDescription, getCategoryByCategoryName(newCategory), newDeadline);
        
        //updating the task in SQL table
        taskOperation = updateTaskOperation;
        taskOperation.execute(task);

    }

}
