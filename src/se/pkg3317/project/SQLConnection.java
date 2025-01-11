/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.pkg3317.project;

/**
 *
 * @author anil
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.*;
import java.util.ArrayList;

public class SQLConnection {

    private static Connection connection;

    public SQLConnection() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/task_db", "root", "asdbnm1122");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addTask(Task task) {
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO tasks (taskName, description, category, deadline) VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, task.getTaskName());
            stmt.setString(2, task.getDesc());
            stmt.setString(3, task.getCategory().toString());
            stmt.setDate(4, Date.valueOf(task.getDeadline()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTask(String taskName) {
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM tasks WHERE taskName = ?")) {
            stmt.setString(1, taskName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listTasksByCategory(Category work, Category home, Category holiday) {
        try (Statement stmt = connection.createStatement()) {
            String query = "SELECT * FROM tasks";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String taskName = rs.getString("taskName");
                String description = rs.getString("description");
                String categoryStr = rs.getString("category");
                String deadline = rs.getString("deadline");

                if (categoryStr.equals("work")) {
                    Task task = new Task(taskName, description, work, deadline);
                    work.addTask(task);
                } else if (categoryStr.equals("home")) {
                    Task task = new Task(taskName, description, home, deadline);
                    home.addTask(task);
                } else if (categoryStr.equals("holiday")) {
                    Task task = new Task(taskName, description, holiday, deadline);
                    holiday.addTask(task);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateTask(Task task) {
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE tasks description = ?, category = ?, deadline = ? WHERE taskName = ?")) {
            stmt.setString(1, task.getDesc());
            stmt.setString(2, task.getCategory().getCategoryName());
            stmt.setDate(3, Date.valueOf(task.getDeadline()));
            stmt.setString(4, task.getTaskName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
