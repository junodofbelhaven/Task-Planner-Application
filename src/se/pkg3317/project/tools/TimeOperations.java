/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.pkg3317.project.tools;

/**
 *
 * @author No2Mo
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import java.util.Timer;
import java.util.TimerTask;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import se.pkg3317.project.MVC.TaskModel;
import se.pkg3317.project.MVC.TaskView;
import se.pkg3317.project.MVC.User;
import se.pkg3317.project.decorator.BasicMessage;
import se.pkg3317.project.decorator.BirthdayMessage;
import se.pkg3317.project.decorator.Message;

public class TimeOperations {

    private TaskModel taskModel;
    private TaskView taskView;
    private JLabel dayLabel;
    private JLabel dateLabel;
    private LocalDate currentDate;
    private Timer timer;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM");
    User user = new User("ozanil", "15.01");

    public TimeOperations(TaskModel taskModel, TaskView taskView, String startDate) {

        this.taskView = taskView;
        this.taskModel = taskModel;
        dayLabel = taskView.DayLabel;
        dateLabel = taskView.DateLabel;
        timer = new Timer();

        int currentYear = LocalDate.now().getYear();
        currentDate = LocalDate.parse(startDate + "." + currentYear, DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        dayLabel.setText(capitalize(currentDate.getDayOfWeek().toString().toLowerCase()));
        dateLabel.setText(currentDate.format(formatter));

    }

    public static Date stringToDate(String string) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date date = dateFormat.parse(string);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void start() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateDate();
                DefaultTableModel tableModel = (DefaultTableModel) taskView.getNotificationTable().getModel();
                tableModel.setRowCount(0);
                taskModel.sendNotificationAllCategories(taskView);
                isBirthday();
            }
        }, 5000, 5000); // Update every 5 seconds, start after 5 seconds. 
    }

    public void stop() {
        timer.cancel();
    }

    private void updateDate() {

        currentDate = currentDate.plusDays(1);

        String newDay = capitalize(currentDate.getDayOfWeek().toString().toLowerCase());
        String newDate = currentDate.format(formatter);

        SwingUtilities.invokeLater(() -> {
            dayLabel.setText(newDay);
            dateLabel.setText(newDate);
        });
    }

    public void isBirthday() {
        if (taskView.getDateLabelText().equals(user.birthDate)) {
            Message message = new BasicMessage();
            message = new BirthdayMessage(message, user);
            taskView.BirthdayLabel.setText(message.getMessage());
        } else {
            taskView.BirthdayLabel.setText("");
        }
    }

    private String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

}
