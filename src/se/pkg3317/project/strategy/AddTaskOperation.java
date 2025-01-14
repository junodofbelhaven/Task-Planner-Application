/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.pkg3317.project.strategy;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import se.pkg3317.project.tools.SQLConnection;
import se.pkg3317.project.MVC.Task;

/**
 *
 * @author No2Mo
 */
public class AddTaskOperation implements TaskOperation {

    @Override
    public void execute(Task task) {
        try (PreparedStatement stmt = SQLConnection.getConnection().prepareStatement("INSERT INTO tasks (taskName, description, category, deadline) VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, task.getTaskName());
            stmt.setString(2, task.getDesc());
            stmt.setString(3, task.getCategory().toString());
            stmt.setDate(4, Date.valueOf(task.getDeadline()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
