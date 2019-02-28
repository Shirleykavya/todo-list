import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.*;
import java.text.SimpleDateFormat;

import java.io.InputStreamReader;

public class TaskManager extends Tasks {
    private BufferedReader input;
    private ArrayList<Tasks> taskList;
    final int MAX_ATTEMPT = 2;


    public TaskManager() throws IOException, ClassNotFoundException {
        input = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
    }

    public static void welcome() {
        System.out.println("******************* Welcome to TO DO List *******************");

        System.out.print("Menu: ");
        System.out.println("1. Show Task List (By date or Project)");
        System.out.println("      2. Add New Task");
        System.out.println("      3. Edit Task(Update, Mark as done, Remove)");
        System.out.println("      4. Save and Quit");
    }

    private String userInput() {
        String inputLine;
        inputLine = null;
        try {
            inputLine = input.readLine();
            //inputLine = sc.next();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return inputLine;
    }

    public int getUserOption(){
        try {
            return Integer.parseInt(userInput());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public void process(int choice) {
        choice = sc.nextInt();
            switch (choice) {
            case 1:
                System.out.println("Task List");
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
                addTasks();
                break;

            case 3:
                System.out.println("Edit Task(Update, Mark as done, Remove)");
                break;

            case 4:
                System.out.println("Save and Quit");
                break;

            default:
                System.out.println("Invalid choice");
                welcome();
                break;

        }
    }

    public void addNewTask(String title, String project, Date dueDate, String taskStatus) {
        Tasks task = new Tasks(getNewTaskId(), title, project, dueDate, taskStatus);
        Tasks.getTasks().add(task);
    }

    public  void addTasks() {
        Tasks tasks = new Tasks();
        Scanner in = new Scanner(System.in);
        System.out.print("Enter Task Title:");
        String title = in.next();
        System.out.print("Enter Project Name:");
        String project = in.next();
        System.out.print("Enter Due date(yyyy-MM-dd):");
        Date dueDate = verifyDueDateFormat();

        tasks.setTaskId();
        tasks.setTitle(title);
        tasks.setProject(project);
        tasks.setDueDate(dueDate);
        tasks.setTaskStatus();


        taskList.add(tasks);

        for(Tasks task: taskList)
            System.out.println(task.getTaskId() + " " + task.getTitle() + " " + task.getProject() + " " + task.getDueDate() + " " +
                    task.getTaskStatus());

        System.out.print("Enter Another Record? (Y/N)");
        String word = in.next();
        word = word.toUpperCase();

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
                    System.out.print("Enter Due date(yyyy-MM-dd):");
                } else {
                    return newDate;
                }
            } catch (java.text.ParseException e) {
                System.out.println("Wrong format, should be (yyyy-MM-dd). Please try again");
                System.out.print("Enter Due date(yyyy-MM-dd):");
            }
            count++;
        }
        return null;
    }



}

