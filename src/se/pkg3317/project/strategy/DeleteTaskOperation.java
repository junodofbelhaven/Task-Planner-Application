/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.pkg3317.project.strategy;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import se.pkg3317.project.tools.SQLConnection;
import se.pkg3317.project.MVC.Task;

/**
 *
 * @author No2Mo
 */
public class DeleteTaskOperation implements TaskOperation {

    @Override
    public void execute(Task task) {
        try (PreparedStatement stmt = SQLConnection.getConnection().prepareStatement("DELETE FROM tasks WHERE taskName = ?")) {
            stmt.setString(1, task.getTaskName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
