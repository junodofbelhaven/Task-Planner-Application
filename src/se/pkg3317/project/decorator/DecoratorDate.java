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
   
    
    @Override
    public String getMessage() {
        return message.getMessage() + "," + LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE) + ")";
    }
    
    

}
