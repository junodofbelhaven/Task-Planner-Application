package se.pkg3317.project.decorator;

import se.pkg3317.project.MVC.Task;

public class NotificationMessage extends Decorator {

    Message message;
    Task task;

    public NotificationMessage(Message message, Task task) {
        this.message = message;
        this.task = task;
    }

    @Override
    public String getMessage() {
        return message.getMessage() + task.getTaskName() + " has 1 day to end. ";
    }
}
