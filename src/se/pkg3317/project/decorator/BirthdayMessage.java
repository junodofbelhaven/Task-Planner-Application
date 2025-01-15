package se.pkg3317.project.decorator;

import se.pkg3317.project.MVC.User;

public class BirthdayMessage extends Decorator {

    Message message;
    User user;

    public BirthdayMessage(Message message, User user) {
        this.message = message;
        this.user = user;
    }

    @Override
    public String getMessage() {
        return message.getMessage() + "Happy Birthday " + user.name;
    }
}
