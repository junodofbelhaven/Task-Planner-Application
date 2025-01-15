/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.pkg3317.project.decorator;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import se.pkg3317.project.MVC.Task;

/**
 *
 * @author No2Mo
 */
public class DecoratorDay extends Decorator {

    Message message;
    Task task;

    public DecoratorDay(Message message, Task task) {
        this.message = message;
        this.task = task;
    }

    @Override
    public String getMessage() {
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = formatter.format(task.getDeadline());
        LocalDate localDate = LocalDate.parse(formattedDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                
        DayOfWeek deadlineDay = localDate.getDayOfWeek();
        return message.getMessage() + " (" + deadlineDay.toString();
    }

}
