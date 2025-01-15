/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.pkg3317.project.MVC;

import se.pkg3317.project.strategy.AddTaskOperation;
import se.pkg3317.project.strategy.TaskOperation;
import se.pkg3317.project.strategy.DeleteTaskOperation;
import se.pkg3317.project.strategy.UpdateTaskOperation;
import java.text.ParseException;
import javax.swing.JOptionPane;
import se.pkg3317.project.tools.addTaskView;
import java.util.Date;
import se.pkg3317.project.tools.TimeOperations;

/**
 *
 * @author No2Mo
 */
public class TaskController {

    private TaskView taskView;
    private TaskModel model;
    private TaskOperation taskOperation;
    private final addTaskView addTaskView;
    private TaskOperation addTaskOperation;
    private TaskOperation updateTaskOperation;
    private TaskOperation deleteTaskOperation;

    public TaskController(TaskView taskView, TaskModel model, addTaskView addTaskView, TaskOperation addTaskOperation, TaskOperation updateTaskOperation, TaskOperation deleteTaskOperation) {
        this.taskView = taskView;
        this.model = model;
        this.addTaskView = addTaskView;
        this.addTaskOperation = addTaskOperation;
        this.updateTaskOperation = updateTaskOperation;
        this.deleteTaskOperation = deleteTaskOperation;
    }
    
    public void setAddTaskOperation(TaskOperation addTaskOperation) {
        this.addTaskOperation = addTaskOperation;
    }

    public void setUpdateTaskOperation(TaskOperation updateTaskOperation) {
        this.updateTaskOperation = updateTaskOperation;
    }

    public void setDeleteTaskOperation(TaskOperation deleteTaskOperation) {
        this.deleteTaskOperation = deleteTaskOperation;
    }  
       
    private void executeAddTask() throws ParseException {

        Task task;

        String taskName = addTaskView.getTaskNameField().getText();
        String description = addTaskView.getDescriptionTextArea().getText();
        String deadline = addTaskView.getSelectedDay() + "." + addTaskView.getSelectedMonth() + ".2025";
        String categoryName = addTaskView.getSelectedCategory();

        if (taskName.isEmpty() || description.isEmpty() || categoryName.isEmpty() || deadline.isEmpty()) {
            JOptionPane.showMessageDialog(addTaskView, "All fields are required!");
            return;
        }

        Date dateDeadline = TimeOperations.stringToDate(deadline);

        task = new Task(taskName, description, model.getCategoryByCategoryName(categoryName), dateDeadline);

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

        Category category = model.getCategoryByCategoryName(categoryName);
        Task deletedTask = category.getTaskByName(deletedTaskName);

        category.removeTask(deletedTask);

        //deleting the task in SQL table
        taskOperation = deleteTaskOperation;
        taskOperation.execute(deletedTask);

    }

    private void executeUpdateTask() throws ParseException {

        int selectedRow = taskView.getTasklistTable().getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(taskView, "Please select a task to edit.");
            return;
        }

        String taskName = String.valueOf(taskView.getTasklistTable().getModel().getValueAt(selectedRow, 0));
        String categoryName = String.valueOf(taskView.getTasklistTable().getModel().getValueAt(selectedRow, 2));
        Category category = model.getCategoryByCategoryName(categoryName);
        Task task = category.getTaskByName(taskName);

        String newDescription = addTaskView.getDescriptionTextArea().getText();
        String newDeadline = (addTaskView.getSelectedDay() + "." + addTaskView.getSelectedMonth() + ".2025").trim();
        String newCategory = addTaskView.getSelectedCategory();

        Date dateNewDeadline = TimeOperations.stringToDate(newDeadline);

        if (newDescription.isEmpty() || newCategory.isEmpty() || newDeadline.isEmpty()) {
            JOptionPane.showMessageDialog(taskView, "All fields are required!");
            return;
        }

        task.updateTaskVariables(newDescription, model.getCategoryByCategoryName(newCategory), dateNewDeadline);

        //updating the task in SQL table
        taskOperation = updateTaskOperation;
        taskOperation.execute(task);

    }

}
