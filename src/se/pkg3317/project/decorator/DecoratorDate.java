/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.pkg3317.project.decorator;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import se.pkg3317.project.MVC.Task;

/**
 *
 * @author No2Mo
 */
public class DecoratorDate extends Decorator {

    Message message;
    Task task;

    public DecoratorDate(Message message, Task task) {
        this.message = message;
        this.task = task;
    }

    @Override
    public String getMessage() {
        return message.getMessage() + "," + task.getDeadline().toString() + ")";
    }

}
