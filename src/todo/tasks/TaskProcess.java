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
import java.util.List;
import java.util.stream.Collectors;
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

    public void tasksByProject() {
        Collections.sort(taskList, new SortByProject());
        showTaskList();
        // for(Tasks task: taskList)
        //  System.out.println(task.getTaskId() + "##" + task.getTitle() + "##" + task.getProject() +
        //     "##" + task.getDueDate() + "##" + task.getStatus());
    }

    class SortByProject implements Comparator<Tasks>{
        public int compare(Tasks task1, Tasks task2){
            return task1.getProject().compareTo(task2.getProject());
        }
    }

    class SortByDate implements Comparator<Tasks>{
        public int compare(Tasks task1, Tasks task2){
            return task1.getDueDate().compareTo(task2.getDueDate());
        }
    }

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
        showTaskList();
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

    public void updatePrintList() {
        System.out.println("The Updated new Task List is");
        showTaskList();
    }
}
