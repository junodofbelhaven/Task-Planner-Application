
import se.pkg3317.project.MVC.Task;
import se.pkg3317.project.MVC.TaskView;
import se.pkg3317.project.tools.SQLConnection;
import se.pkg3317.project.tools.TimeOperations;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author anil
 */
public class TaskPlanner {
    
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                
                
                
                Task task = new Task("plan work", "a", work, TimeOperations.stringToDate("01.03.2025"));
                TaskView view = new TaskView();
                view.setVisible(true);
                SQLConnection c = new SQLConnection(view);
                c.loadTasksToTable();
            }
        });
    }
    
    
    
}
