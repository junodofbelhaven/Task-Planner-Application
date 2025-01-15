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

public class TimeOperations {

    private JLabel dayLabel;
    private JLabel dateLabel;
    private LocalDate currentDate;
    private Timer timer;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM");

    public TimeOperations(JLabel dayLabel, JLabel dateLabel, String startDate) {

        this.dayLabel = dayLabel;
        this.dateLabel = dateLabel;
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

    private String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
