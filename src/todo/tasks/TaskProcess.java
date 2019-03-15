/**
 * This is the main controller class of ToDoApp.
 * It shows output to the user according to his selection.
 */

package todo.tasks;

import todo.storage.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;



public class TaskProcess {

    private BufferedReader input;
    private ArrayList<Tasks> taskList;
    private FileReader filereader;
    Scanner sc = new Scanner(System.in);
    int max_attempt = 2;
    TaskStorage taskStorage;

    public TaskProcess() throws IOException {
        input = new BufferedReader(new InputStreamReader(System.in) );
        filereader = new FileReader();
        taskStorage = filereader.readFile();
        taskList = new ArrayList<>();
        if(taskStorage!=null && taskStorage.getTasks()!=null)
            taskList = taskStorage.getTasks();
    }

    public ArrayList<Tasks> getTasks() {
        return taskList;
    }

    public void addto(Tasks task) {
        taskList.add(task);
    }

    public int countTasks(boolean status) {
        return Long.valueOf(taskList.stream().filter(task -> task.getStatus() == status).count()).intValue();
    }

    /**
     * Add a task, accepts input from user for a task like title, project, due date.
     */
    public void addNewTask() throws IOException {
        System.out.print("Enter Task Title: ");
        String title = sc.next();
        System.out.print("Enter Project Name: ");
        String project = sc.next();
        System.out.print("Enter Due date(dd-MM-yyyy): ");
        Date dueDate = verifyDueDateFormat();

        Tasks newTask = new Tasks(taskList.size()+1, title, project, dueDate, false);
        taskList.add(newTask);
        showTaskList();
    }

    public void showTaskList() {
        for(Tasks task: taskList) {
            String status="";
            if(task.getStatus())
                status = "Done";
            else
                status = "To Do";
            System.out.println(task.getTaskId() + "##" + task.getTitle() + "##" + task.getProject() +
                    "##" + task.getDueDate() + "##" + status);
        }
    }

    /**
     * Verifies the date format
     * Gives user a maximum of two attempts if he enters wrong input
     *
     * @return date in correct format otherwise returns null value
     */
    public Date verifyDueDateFormat() throws IOException {
        Date newDate = null;
        int count = 1;
        while (count <= max_attempt) {
            try {
                String inputDate = userInput();
                newDate = new SimpleDateFormat("dd-MM-yyyy").parse(inputDate);
                //String formattedDate = verifyDueDateFormat();
                if(newDate.before(new Date())) {
                    System.out.println("Oops! due date can’t be in past. Please try again.");
                    System.out.print("Enter Due date(dd-MM-yyyy):");
                } else {
                    return newDate;
                }
            } catch (java.text.ParseException e) {
                System.out.println("Date format should be dd-MM-yyyy. Please try again");
                System.out.print("Enter Due date(dd-MM-yyyy):");
            }
            count++;
        }
        return null;
    }

    public String userInput() throws IOException {
        String inputLine = null;
        inputLine = input.readLine();
        return inputLine;
    }

    public void saveToFile()throws IOException {
        taskStorage = new TaskStorage();
        taskStorage.setTasks(taskList);
        filereader.saveTask(taskStorage);
    }

    /**
     * Filter tasklist based on project name
     * @return list of tasks filtered by project
     */
    public void tasksByProject(String project) {
        boolean isProjectExist = false;
        System.out.println("List of tasks filtered by Project '" + project + "' are: ");
        for(int i=0; i<taskList.size();i++) {
            if(taskList.get(i).getProject().equalsIgnoreCase(project)) {
                System.out.println(taskList.get(i).getDetails());
                isProjectExist = true;
            }
        }
        if(!isProjectExist) {
            System.out.println("Project '" + project + "' does not exist in the list\"");
        }
    }

    class SortByDate implements Comparator<Tasks>{
        public int compare(Tasks task1, Tasks task2){
            return task1.getDueDate().compareTo(task2.getDueDate());
        }
    }

    /**
     * Shows all the tasks sorted by date
     *
     * @return list of tasks sorted by date
     */
    public void tasksByDate() {
        Collections.sort(taskList,new SortByDate());
        showTaskList();
    }

    public int getUserOption() throws IOException {
        try {
            return Integer.parseInt(userInput());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    /**
     * Update the title of the task with the given id
     *
     * @param id    the id of the task to be updated with new title
     * @param title the new value of title
     */
    public void updateTaskTitle(int id, String title) {
        if(taskList.size()>=id) {
            for(int i=0; i<taskList.size();i++) {
                if(taskList.get(i).getTaskId() == id)
                    taskList.get(i).setTitle(title);
            }
        }
        updatePrintList();
    }

    /**
     * Update the project of the task with the given id
     *
     * @param id      the id of the task to be updated with new project name
     * @param project the new value of project name
     */
    public void updateTaskProject(int id, String project) {
        if(taskList.size()>=id) {
            for(int i=0; i<taskList.size();i++) {
                if(taskList.get(i).getTaskId() == id)
                    taskList.get(i).setProject(project);
            }
        }
        updatePrintList();
    }

    /**
     * Update the due date of the task with the given id
     *
     * @param id      the id of the task to be updated with new due date
     * @param dueDate the new value of due date
     */
    public void updateTaskDueDate(int id, Date dueDate) {
        if(taskList.size()>=id) {
            for(int i=0; i<taskList.size();i++) {
                if(taskList.get(i).getTaskId() == id)
                    taskList.get(i).setDueDate(dueDate);
            }
        }
        updatePrintList();
    }

    /**
     * Display the Updated the list
     */
    public void updatePrintList() {
        System.out.println("The Updated new Task List is");
        showTaskList();
    }
}
