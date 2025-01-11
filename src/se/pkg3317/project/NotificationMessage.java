package se.pkg3317.project;


public class NotificationMessage extends Decorator {
    Message message;
    Task task;

    public NotificationMessage(Message message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message.getMessage() + task.getTaskName() + " has 1 day to end.\n";
    }
}
