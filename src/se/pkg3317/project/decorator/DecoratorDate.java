/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.pkg3317.project.decorator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author No2Mo
 */
public class DecoratorDate extends Decorator {
    
    Message message;
    
    
    public DecoratorDate(Message message) {
        this.message = message;   
    }

    @Override
    public String getMessage() {
       String date = LocalDate.parse(task.getDeadline().toString(),DateTimeFormatter.ofPattern("dd.MM")).format(DateTimeFormatter.ofPattern("dd.MM"));
        return message.getMessage() + date;
    }
    
    public static void main(String[] args) {
        
    }

}
