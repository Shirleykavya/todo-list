import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.util.*;
import java.text.*;
import java.io.DataInputStream;
import java.util.List;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.Iterator;
import java.io.InputStreamReader;

public class TaskManager extends Tasks {

    private ArrayList<Tasks> taskList;

    private BufferedReader input;
    final int MAX_ATTEMPT = 2;


    public TaskManager()  {
        input = new BufferedReader(new InputStreamReader(System.in));
        taskList = new ArrayList<Tasks>();
    }

    public static void welcome() {

        System.out.println("Welcome to TO DO List");

        System.out.print("Menu: ");
        System.out.println("1. Show Task List");
        System.out.println("      2. Add Task");
        System.out.println("      3. Edit Task");
        System.out.println("      4. Remove Task");
        System.out.print("Select Option: ");

    }

    private String userInput() {
        String inputLine;
        //System.out.print("> ");
        inputLine = null;
        try {
            inputLine = input.readLine();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return inputLine;
    }

    public  void addTasks(){
        Tasks tasks = new Tasks();


        Scanner in = new Scanner(System.in);
        System.out.print("Enter Task Title:");
        String title = in.next();
        System.out.print("Enter Project Name:");
        String project = in.next();
        System.out.print("Enter Due date(yyyy-MM-dd):");
        Date dueDate = verifyDueDateFormat();

        //System.out.print("Enter Task Status:");
        //String taskStatus = in.next();

        //tasks.

        tasks.setTaskId();
        tasks.setTitle(title);
        tasks.setProject(project);
        tasks.setDueDate(dueDate);
        tasks.setTaskStatus("Done");


        taskList.add(tasks);


        for(Tasks task: taskList)
            System.out.println(task.getTaskId() + " " + task.getTitle() + " " + task.getProject() + " " + task.getDueDate() + " " +
                    task.getTaskStatus());

        System.out.print("Enter Another Record? (Y/N)");
        String word = in.next();
        word = word.toUpperCase();

    }


    public void printList(){
        System.out.println("**************************************** To Do List ****************************************");

        for(Tasks task: taskList)
            System.out.println(task.getTaskId() + " " + task.getTitle() + " " + task.getDueDate() + " " + task.getProject() + " " +
                    task.getTaskStatus());

    }


    public Date verifyDueDateFormat() {
        Date newDate = null;
        int count = 1;
        while (count <= MAX_ATTEMPT) {
        try {
            String inputDate = userInput();
            newDate = new SimpleDateFormat("yyyy-MM-dd").parse(inputDate);
            if(newDate.before(new Date())) {
                System.out.println("Oops! due date can't be in past. Please try again.");
                System.out.println("Enter Due date(yyyy-MM-dd):");
            } else {
                return newDate;
            }
        } catch (java.text.ParseException e) {
            System.out.println("Wrong format, should be (yyyy-MM-dd). Please try again");
            System.out.println("Enter Due date(yyyy-MM-dd):");
        }
            count++;
        }
        return null;
    }

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        taskManager.welcome();


        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        boolean option = true;
       // while(true) {
            switch (choice) {
                case 1:
                    System.out.println("Task List");
                    taskManager.printList();

                    System.out.print("Press 'Y' if you wants to go back to Menu:");
                    String go = sc.next();
                    go = go.toUpperCase();
                        welcome();
                    //while (option) {
                        //taskManager.userInput();
                    //}
                    break;

                case 2:
                    System.out.println("Add a Task");
                    taskManager.addTasks();
                    break;

                case 3:
                    System.out.println("Edit Task");
                    break;

                case 4:
                    System.out.println("Save and Quit");
                    break;

                default:
                    System.out.println("Invalid choice");
                    taskManager.welcome();
                    break;
            }//end of switch
        //}

    }

}

