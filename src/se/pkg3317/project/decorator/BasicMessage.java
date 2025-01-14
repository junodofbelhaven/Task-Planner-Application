package se.pkg3317.project.decorator;

import se.pkg3317.project.MVC.Task;

public class BasicMessage extends Message{

    Task task;

    public BasicMessage(Task task) {
        message = "";
        this.task = task;
    }
    
    
    @Override
    public String getMessage() {
      return message;
    }
}
