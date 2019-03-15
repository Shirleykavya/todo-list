/**
 * Accept & parse input from the user.
 */


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
    Scanner sc = new Scanner(System.in);

    public TaskManager() throws IOException {
        taskProcess = new TaskProcess();
    }

    public void welcome() {
        System.out.println("Welcome to TO DO List \n");
        System.out.println("Main Menu: \n");
        System.out.println("1. Show Task List");
        System.out.println("2. Add New Task");
        System.out.println("3. Edit Task (Edit, Mark as done, Remove)");
        System.out.println("4. Save and Quit");
        System.out.println("5. Quit");
    }

    public void process() throws IOException {
        int choice;
        System.out.print("Select Option: ");
        choice = sc.nextInt();
        switch (choice) {
            case 1:
                System.out.println("************************** Task List **************************\n");
                taskList();
                break;
            case 2:
                System.out.println("************************** Add Task **************************\n");
                addTask();
                break;
            case 3:
                System.out.println("************************** Edit task **************************\n");
                editList();
                break;
            case 4:
                System.out.println("************************** Save and Quit **************************\n");
                taskProcess.saveToFile();
                System.out.println("Tasks are Saved.\n");
                menuExit();
                break;
            case 5:
                System.out.println("Logging out of To Do List.....!");
                exit();
                break;
            default:
                System.out.println("Please provide correct value!\n");
                run();
        }
    }

    public void run() throws IOException {
        welcome();
        process();
    }

    private void menuExit() throws IOException {
        System.out.print("Back to Menu(Y)/Exit(N): ");
        String go = sc.next();
        go = go.toUpperCase();

        if (go.equalsIgnoreCase("Y")) {
            run();
        } else if (go.equalsIgnoreCase("N")) {
            exit();
        } else {
            System.out.println("Unknown input! Please provide correct value.\n");
            menuExit();
        }
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
            System.out.println("Unknown input! Please provide correct value.\n");
            nextTask();
        }
    }

    public void exit() {
        System.exit(0);
    }

    public void taskList() throws IOException {
        taskProcess.showTaskList();
        if (taskProcess.getTasks().size() > 0) {
            System.out.print("To see the Sorted List(Y)/ Back to Menu(N): \n");
            String go = sc.next();
            go = go.toUpperCase();
            if (go.equalsIgnoreCase("Y")) {
                sortedTaskList();
            } else if (go.equalsIgnoreCase("N")) {
                run();
            } else {
                System.out.println("Please provide correct value!\n");
                taskList();
            }
        }
        else {
            System.out.println("No Tasks Exists! Add a Task.\n");
            run();
        }
    }

    public void sortedTaskList() throws IOException{
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
                taskProcess.tasksByProject();
                break;
            default:
                System.out.println("Please provide correct value!");
                sortedTaskList();
        }

        System.out.print("Back to Sorted List Menu(Y)/Main Menu(N): ");
        String go = sc.next();
        go = go.toUpperCase();

        if (go.equalsIgnoreCase("Y")) {
            sortedTaskList();
        } else if (go.equalsIgnoreCase("N")) {
            run();
        } else {
            System.out.println("Incorrect Value. Here is the Main Menu!\n");
            run();;
        }
    }

    public void editList() throws IOException {
        int choice;
        taskProcess.showTaskList();
        if (taskProcess.getTasks().size() > 0) {
            System.out.println("Select Task id to edit: ");
            int id = sc.nextInt();
            if (id != -1 && taskProcess.getTasks().size()>=id) {
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
                    default:
                        System.out.println("Please provide correct value!");
                        editList();
                }
            }
            else {
                System.out.println("Select valid id!");
                editList();
            }
        }
        else {
            System.out.println("No Tasks Exists! Add a Task.\n");
            run();
        }
    }

    public void updateTask(int id) throws IOException{

        System.out.println("1. Update Title");
        System.out.println("2. Update Project");
        System.out.println("3. Update Date");
        System.out.print("Select Option: ");
        int choice = sc.nextInt();

        switch (choice) {
            case 0:
                return;
            case 1:
                System.out.println("Enter new Task Title: ");
                String newTitle = sc.next();
                taskProcess.updateTaskTitle(id, newTitle);
                break;
            case 2:
                System.out.println("Enter new Project: ");
                String newProject = taskProcess.userInput();
                taskProcess.updateTaskProject(id, newProject);
                break;
            case 3:
                System.out.println("Enter new Due Date(dd-MM-yyyy): ");
                Date newDate = taskProcess.verifyDueDateFormat();
                if (newDate != null) {
                    taskProcess.updateTaskDueDate(id, newDate);
                }
                break;
        }
        editNextTask();
    }

    /**
     * Change the status of a task to true that is mark as done
     */
    private void changeStatus(int id) throws IOException{

        if(taskProcess.getTasks().size()>=id)
            for(int i=0;i<taskProcess.getTasks().size();i++)
                if(taskProcess.getTasks().get(i).getTaskId() == id)
                    taskProcess.getTasks().get(i).setStatus(true);
        System.out.println("Your task is now mark as done so the status is changed from false to true " +
                " and here is the new list\n");
        taskProcess.showTaskList();
        editNextTask();
    }

    private void removeTask(int id) throws IOException {
        if(taskProcess.getTasks().size()>=id)
            for(int i=0;i<taskProcess.getTasks().size();i++)
                if(taskProcess.getTasks().get(i).getTaskId() == id)
                    taskProcess.getTasks().remove(id);
        System.out.println("Task removed!!!");
        System.out.println("Now The Task List is:");
        taskProcess.showTaskList();
        editNextTask();
    }

    private void editNextTask() throws IOException {

        System.out.print("Want to Edit Another Task(Y/N): ");
        String go = sc.next();
        go = go.toUpperCase();

        if (go.equalsIgnoreCase("Y")) {
            editList();
        } else if (go.equalsIgnoreCase("N")) {
            run();
        } else {
            System.out.println("IPlease provide correct value!\n");
            editNextTask();
        }

    }

}


