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
import se.pkg3317.project.MVC.TaskView;
import se.pkg3317.project.MVC.Task;
import se.pkg3317.project.MVC.Category;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import se.pkg3317.project.MVC.Category;
import se.pkg3317.project.MVC.Task;
import se.pkg3317.project.MVC.TaskView;

public class SQLConnection {

    private static Connection connection;
    TaskView view;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public static Connection getConnection() {
        return connection;
    }

    public SQLConnection(TaskView view) {

        String URL = "jdbc:mysql://localhost:3306/task_db";
        String username = "root";
        String password = "asdbnm1122";

        this.view = view;

        try {
            this.connection = DriverManager.getConnection(URL, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listTasksByCategory(Category work, Category home, Category holiday) throws ParseException {
        try (Statement stmt = connection.createStatement()) {
            String query = "SELECT * FROM tasks";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String taskName = rs.getString("taskName");
                String description = rs.getString("description");
                String categoryStr = rs.getString("category");
                String deadline = rs.getString("deadline");

                Date dateDeadline = (Date) dateFormat.parse(deadline);

                if (categoryStr.equals("work")) {
                    Task task = new Task(taskName, description, work, dateDeadline);
                    work.addTask(task);
                } else if (categoryStr.equals("home")) {
                    Task task = new Task(taskName, description, home, dateDeadline);
                    home.addTask(task);
                } else if (categoryStr.equals("holiday")) {
                    Task task = new Task(taskName, description, holiday, dateDeadline);
                    holiday.addTask(task);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadTasksToTable() {

        String query = "SELECT taskName, shortDescription, category, deadline FROM tasks";

        DefaultTableModel model = (DefaultTableModel) view.getTasklistTable().getModel();

        model.setRowCount(0);

        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String taskName = resultSet.getString("taskName");
                String shortDescription = resultSet.getString("shortDescription");
                String category = resultSet.getString("category");
                Date deadline = resultSet.getDate("deadline");

                // Her satırı tabloya ekle
                model.addRow(new Object[]{taskName, shortDescription, category, deadline});
            }

        } catch (SQLException e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null,
                    "Failed to load the data: " + e.getMessage(),
                    "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

}
