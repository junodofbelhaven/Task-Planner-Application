package se.pkg3317.project.decorator;

import se.pkg3317.project.MVC.TaskView;
import se.pkg3317.project.MVC.User;

public class BirthdayMessage extends Decorator {

    Message message;
    TaskView view;
    User user;
    
    public BirthdayMessage(Message message, TaskView view, User user) {
        this.message = message;
        this.view = view;
        this.user = user;
    }

    public boolean isBirthday() {
        if (view.getDateLabelText().equals(user.birthDate)) {
            return true;
        }
        return false;
    }

    @Override
    public String getMessage() {
        if (isBirthday()) {
            return message.getMessage() + "Happy Birthday " + user.name;
        }
        return "";
    }
}
