/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.pkg3317.project.MVC;

import java.util.Date;
import se.pkg3317.project.tools.SQLConnection;
import se.pkg3317.project.tools.TimeOperations;

/**
 *
 * @author anil
 */
public class TaskModel {

    private final Category work;
    private final Category holiday;
    private final Category home;
    private SQLConnection sQLConnection;
    private Date anilinDG = TimeOperations.stringToDate("20.01.2025");
    private Date ozaninDG = TimeOperations.stringToDate("25.01.2025");

    public TaskModel(TaskView view) {
        work = new Category("work");
        holiday = new Category("holiday");
        home = new Category("home");
        sQLConnection = new SQLConnection(view);
    }

    public Category getCategoryByCategoryName(String categoryName) {

        return switch (categoryName) {
            case "Work" -> work;
            case "Holiday" -> holiday;
            case "Home" -> home;
            default -> null;
        };
    }

    public SQLConnection getsQLConnection() {
        return sQLConnection;
    }
        
    public Date getAnilinDG() {
        return anilinDG;
    }

    public Date getOzaninDG() {
        return ozaninDG;
    } 
   
}
