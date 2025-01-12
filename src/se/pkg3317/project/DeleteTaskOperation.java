/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.pkg3317.project;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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
            task.getCategory().removeTask(task);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
