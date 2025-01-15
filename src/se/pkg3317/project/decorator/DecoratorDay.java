/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.pkg3317.project.decorator;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 *
 * @author No2Mo
 */
public class DecoratorDay extends Decorator {

  Message message;
  
    public DecoratorDay(Message message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
     DayOfWeek today = LocalDate.now().getDayOfWeek();
     return message.getMessage() + today.toString();
    }

}
