/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.pkg3317.project.strategy;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import se.pkg3317.project.tools.SQLConnection;
import se.pkg3317.project.MVC.Task;

/**
 *
 * @author No2Mo
 */
public class UpdateTaskOperation implements TaskOperation {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    
    @Override
    public void execute(Task task) {
        try (PreparedStatement stmt = SQLConnection.getConnection().prepareStatement("UPDATE Tasks SET description = ?, category = ?, deadline = ? WHERE taskName = ?")) {
            stmt.setString(1, task.getDesc());
            stmt.setString(2, task.getCategory().getCategoryName());
            String deadline = dateFormat.format(task.getDeadline());
            stmt.setDate(3, Date.valueOf(deadline));
            stmt.setString(4, task.getTaskName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
