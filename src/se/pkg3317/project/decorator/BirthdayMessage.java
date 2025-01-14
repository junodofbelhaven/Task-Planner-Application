package se.pkg3317.project.decorator;

import se.pkg3317.project.MVC.TaskView;

public class BirthdayMessage extends Decorator {

    Message message;
    TaskView view;
    Date birthdate;

    public BirthdayMessage(Message message, Date birthdate) {
        this.message = message;
        this.birthdate = birthdate;
    }

    public boolean isBirthday() {
        if (view.getDateLabelText().equals(birthdate)) {
            return true;
        }
        return false;
    }

    @Override
    public String getMessage() {
        if (isBirthday()) {
            return message.getMessage() + "Happy Birthday Userrr!";
        }
            return message.getMessage();
    }
}
