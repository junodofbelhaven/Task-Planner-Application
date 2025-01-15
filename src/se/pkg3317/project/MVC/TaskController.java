/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.pkg3317.project.MVC;

import se.pkg3317.project.strategy.TaskOperation;
import java.text.ParseException;
import javax.swing.JOptionPane;
import se.pkg3317.project.tools.AddTaskView;
import java.util.Date;
import se.pkg3317.project.tools.TimeOperations;
import se.pkg3317.project.tools.UpdateTaskView;

/**
 *
 * @author No2Mo
 */
public class TaskController {

    private final TaskView taskView;
    private final TaskModel model;
    private final AddTaskView addTaskView;
    private final UpdateTaskView updateTaskView;

    public TaskController(TaskModel model) {
        this.taskView = new TaskView(model, this);
        this.model = model;
        this.addTaskView = taskView.getAddTaskView();        
        this.updateTaskView = taskView.getUpdateTaskView(); 
        
        taskView.setVisible(true);
    }

    private boolean validateInputs(String taskName, String description, String categoryName, String deadline) {
        return !taskName.isEmpty() && !description.isEmpty() && !categoryName.isEmpty() && !deadline.isEmpty();
    }  

    public void executeAddTask() {
        String taskName = addTaskView.getTaskNameField().getText();
        String description = addTaskView.getDescriptionTextArea().getText();
        String deadline = addTaskView.getSelectedDay() + "." + addTaskView.getSelectedMonth() + ".2025";
        String categoryName = addTaskView.getSelectedCategory();

        if (!validateInputs(taskName, description, categoryName, deadline)) {
            JOptionPane.showMessageDialog(addTaskView, "All fields are required!");
            return;
        }

        Date dateDeadline = TimeOperations.stringToDate(deadline);
        Task task = new Task(taskName, description, model.getCategoryByCategoryName(categoryName), dateDeadline);

        model.handleAddTask(task);
    }

    public void executeDeleteTask() {
        int selectedRow = taskView.getTasklistTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(taskView, "Please select a task to delete.");
            return;
        }

        String deletedTaskName = String.valueOf(taskView.getTasklistTable().getModel().getValueAt(selectedRow, 0));
        String categoryName = String.valueOf(taskView.getTasklistTable().getModel().getValueAt(selectedRow, 2));
        Category category = model.getCategoryByCategoryName(categoryName);
        Task deletedTask = category.getTaskByName(deletedTaskName);
        category.removeTask(deletedTask);

        model.handleDeleteTask(deletedTask);
    }

    public void executeUpdateTask() {
        int selectedRow = taskView.getTasklistTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(taskView, "Please select a task to edit.");
            return;
        }

        String taskName = String.valueOf(taskView.getTasklistTable().getModel().getValueAt(selectedRow, 0));
        String categoryName = String.valueOf(taskView.getTasklistTable().getModel().getValueAt(selectedRow, 2));
        
        
        Category category = model.getCategoryByCategoryName(categoryName);
        Task task = category.getTaskByName(taskName);
        
        updateTaskView.setTaskNameField(taskName);
        String newDescription = updateTaskView.getDescriptionTextArea().getText();
        String newDeadline = updateTaskView.getSelectedDay() + "." + updateTaskView.getSelectedMonth() + ".2025";
        String newCategory = updateTaskView.getSelectedCategory();

        if (!validateInputs(taskName, newDescription, newCategory, newDeadline)) {
            JOptionPane.showMessageDialog(taskView, "All fields are required!");
            return;
        }

        Date dateNewDeadline = TimeOperations.stringToDate(newDeadline);
        task.updateTaskVariables(newDescription, model.getCategoryByCategoryName(newCategory), dateNewDeadline);
        //buna bak
        
        model.handleUpdateTask(task);
    }
}
