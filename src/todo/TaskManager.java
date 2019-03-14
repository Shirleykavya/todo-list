package todo;

import todo.tasks.TaskProcess;
import todo.tasks.Tasks;
import todo.tasks.TaskStorage;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Date;

public class TaskManager {

    private TaskProcess taskProcess;
    private TaskStorage taskStorage;
    Scanner sc = new Scanner(System.in);

    public TaskManager() throws IOException {
        taskProcess = new TaskProcess();
    }

    public void welcome() {
        System.out.println("Welcome to TO DO List");
        System.out.println("Menu: ");
        System.out.println("1. Show Task List");
        System.out.println("2. Add New Task");
        System.out.println("3. Edit Task (Edit, Mark as done, Remove)");
        System.out.println("4. Save and Quit");
    }

    public void process() throws IOException {
        int choice;
        System.out.print("Select Option: ");
        choice = sc.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Task List");
                taskList();
                break;
            case 2:
                System.out.println("Add Task");
                addTask();
                break;
            case 3:
                System.out.println("Edit task");
                editList();
                break;
            case 4:
                System.out.println("Save and Quit");
                taskProcess.saveToFile();
                System.out.println("Logging out of To Do List.....!");
                exit();
                break;
            default:
                System.out.println("Please provide correct value");
                run();
        }
    }

    public void run() throws IOException {
        welcome();
        process();
    }

    public void addTask() throws IOException {
        taskProcess.addNewTask();
        nextTask();
    }

    private void nextTask() throws IOException {
        System.out.print("Enter Another Record(Y/N): ");
        String go = sc.next();
        go = go.toUpperCase();

        if (go.equalsIgnoreCase("Y")) {
            addTask();
        } else if (go.equalsIgnoreCase("N")) {
            run();
        } else {
            System.out.println("Unknown input! Please provide correct value.");
            nextTask();
        }
    }

    public void exit() {
        System.exit(0);
    }

    public void taskList() throws IOException {
        int choice;
        System.out.println("1. Show Task List Ordered by Due date");
        System.out.println("2. Show Task List Filtered by Project");
        System.out.print("Select Option: ");
        choice = sc.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Task List Ordered by Due date");
                taskProcess.tasksByDate();
                break;
            case 2:
                System.out.println("Task List Filtered by Project");
                //printTaskList(tasksProcess.tasksByProject());
                break;
            default:
                System.out.println("Please provide correct value");
                taskList();
        }
        System.out.print("Back to Menu(Y/N): ");
        String go = sc.next();
        go = go.toUpperCase();

        if (go.equalsIgnoreCase("Y")) {
            run();
        } else if (go.equalsIgnoreCase("N")) {
            exit();
        } else {
            System.out.println("Unknown input!");
            exit();
        }
    }

    public void editList() throws IOException {
        int choice;
        int id = taskProcess.verifyId();
        if (id != -1) {
            System.out.println("1. Update Task");
            System.out.println("2. Mark as done");
            System.out.println("3. Remove Task");
            System.out.print("Select Option: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Update Task");
                    updateTask(id);
                    break;
                case 2:
                    System.out.println("Mark as done");
                    changeStatus(id);
                    break;
                case 3:
                    System.out.println("Remove Task");
                    removeTask(id);
                    break;
            }
        }
    }
    public void updateTask(int id) throws IOException{

        boolean status = false;
        boolean choice = true;
        while (choice) {

        System.out.println("1. Update Title");
        System.out.println("2. Update Project");
        System.out.println("3. Update Date");
        System.out.print("Select Option: ");
        int updateField = taskProcess.getUserOption();

        switch (updateField) {
            case 0:
                return;
            case 1:
                System.out.println("Enter new Task Title");
                String newTitle = taskProcess.userInput();
                status = taskProcess.updateTaskTitle(id, newTitle);
                break;
            case 2:
                System.out.println("Enter new Project");
                String newProject = taskProcess.userInput();
                status = taskProcess.updateTaskProject(id, newProject);
                break;
            case 3:
                System.out.println("Enter new Due Date");
                Date newDate = taskProcess.verifyDueDateFormat();
                if (newDate != null) {
                    status = taskProcess.updateTaskDueDate(id, newDate);
                }
                break;
        }
            if (status) {
                System.out.println("Updated the list and here is the new updated list");
                taskStorage.getTasks();
                choice = false;
            }
            else {
                System.out.println(" If you want to see the options again press 0 otherwise continue");
                choice = true;
            }

        }
    }

    /**
     * Change the status of a task to true that is mark as done
     */
    private void changeStatus(int id) throws IOException{
        boolean status = false;
        System.out.println("Update Status:");
        String newStatus = taskProcess.userInput();
        status = taskProcess.updateTaskProject(id, newStatus);
        System.out.println("Your task is now mark as done that is changed the status from In Progress to Done " +
                " and here is the new list");
        taskProcess.tasksByDate();
    }

    private void removeTask(int id) {
        taskProcess.removeTask(id);
        System.out.println("Task removed!!!");
        System.out.println("Now The Task List is:");
        taskProcess.tasksByDate();
    }

}



