/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.pkg3317.project.MVC;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import se.pkg3317.project.decorator.BasicMessage;
import se.pkg3317.project.decorator.DecoratorDate;
import se.pkg3317.project.decorator.DecoratorDay;
import se.pkg3317.project.decorator.Message;
import se.pkg3317.project.decorator.NotificationMessage;

/**
 *
 * @author anil
 */
public class Task implements Component {

    private final String taskName;
    private String desc;
    private Category category;
    private Date deadline;
    private Message message;

    public Task(String taskName, String desc, Category category, Date deadline) {
        this.taskName = taskName;
        this.desc = desc;
        this.category = category;
        this.deadline = deadline;
        category.addTask(this);
    }

    public void updateTaskVariables(String desc, Category category, Date deadline) {
        this.desc = desc;
        this.deadline = deadline;
        category.removeTask(this);
        this.category = category;
        category.addTask(this);
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDesc() {
        return desc;
    }

    public Category getCategory() {
        return category;
    }

    public Date getDeadline() {
        return deadline;
    }

    @Override
    public void sendNotification(TaskView view) {
        if (controlDeadline(view)) {
            message = new BasicMessage();
            message = new NotificationMessage(message, this);
            message = new DecoratorDay(message, this);
            message = new DecoratorDate(message, this);
            DefaultTableModel model = (DefaultTableModel) view.getNotificationTable().getModel();
            model.addRow(new Object[]{message.getMessage()});
        }
    }

    public boolean controlDeadline(TaskView view) {

        String labelDateText = view.getDateLabelText();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM");

        String deadlineAsString = formatter.format(deadline);

        if (deadlineAsString.equals(labelDateText)) {
            return true;
        }

        return false;
    }

}
