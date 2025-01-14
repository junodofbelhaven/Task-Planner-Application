/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.pkg3317.project;

/**
 *
 * @author No2Mo
 */
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import java.util.Timer;
import java.util.TimerTask;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TimerOperations {

    private JLabel dayLabel;
    private JLabel dateLabel;
    private LocalDate currentDate; // Use LocalDate for simplicity
    private Timer timer;

    public TimerOperations(JLabel dayLabel, JLabel dateLabel, String startDate) {
        this.dayLabel = dayLabel;
        this.dateLabel = dateLabel;

        // Initialize the current date using the current year and provided day/month
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM");
        int currentYear = LocalDate.now().getYear(); // Use the current year
        this.currentDate = LocalDate.parse(startDate + "." + currentYear, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        this.timer = new Timer();
    }

    public void start() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateDate();
            }
        }, 0, 5000); // Update every 5 seconds
    }

    public void stop() {
        timer.cancel();
    }

    private void updateDate() {
        // Move to the next day
        currentDate = currentDate.plusDays(1);

        // Format the new day and date
        final String newDay = capitalize(currentDate.getDayOfWeek().toString().toLowerCase()); // Get day of the week
        final String newDate = currentDate.format(DateTimeFormatter.ofPattern("dd.MM"));

        // Update labels on the Swing UI thread
        SwingUtilities.invokeLater(() -> {
            dayLabel.setText(newDay);
            dateLabel.setText(newDate);
        });
    }

    private String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
