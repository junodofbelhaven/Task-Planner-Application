
import se.pkg3317.project.MVC.Task;
import se.pkg3317.project.MVC.TaskController;
import se.pkg3317.project.MVC.TaskModel;
import se.pkg3317.project.strategy.AddTaskOperation;
import se.pkg3317.project.strategy.DeleteTaskOperation;
import se.pkg3317.project.strategy.UpdateTaskOperation;


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

                TaskModel model = new TaskModel(new AddTaskOperation(), new UpdateTaskOperation(), new DeleteTaskOperation());
//                TaskController controller = new TaskController(model);
                
                for(Task task : model.holiday.taskList){
                    System.out.println(task.getTaskName());
                    System.out.println(task.getCategory().getCategoryName());
                }
                
                for(Task task : model.work.taskList){
                    System.out.println(task.getTaskName());
                    System.out.println(task.getCategory().getCategoryName());
                }
                
                
            }
        });
    }
    
    
    
}
