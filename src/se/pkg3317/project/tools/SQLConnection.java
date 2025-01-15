/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.pkg3317.project.tools;

/**
 *
 * @author anil
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import se.pkg3317.project.MVC.Category;
import se.pkg3317.project.MVC.Task;
import se.pkg3317.project.MVC.TaskView;

public class SQLConnection {

    private static Connection connection;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public static Connection getConnection() {
        return connection;
    }

    public SQLConnection() {

        String URL = "jdbc:mysql://localhost:3306/task_db";
        String username = "root";
        String password = "asdbnm1122";

        try {
            this.connection = DriverManager.getConnection(URL, username, password);
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
                Date dateDeadline = rs.getDate("deadline");

                if (categoryStr.equals("work")) {
                    Task task = new Task(taskName, description, work, dateDeadline);
                } else if (categoryStr.equals("home")) {
                    Task task = new Task(taskName, description, home, dateDeadline);
                } else if (categoryStr.equals("holiday")) {
                    Task task = new Task(taskName, description, holiday, dateDeadline);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
