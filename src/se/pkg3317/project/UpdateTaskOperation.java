/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.pkg3317.project;

/**
 *
 * @author No2Mo
 */
public class UpdateTaskOperation implements TaskOperation {

    public UpdateTaskOperation() {
    }

    
    @Override
    public void execute(SQLConnection sql, Task task) {
       sql.updateTask(task);
    }
    
}
