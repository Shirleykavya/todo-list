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


public class TaskProcess {

    private BufferedReader input;
    private TaskStorage taskStorage;
    private FileReader filereader;
    Scanner sc = new Scanner(System.in);
    int max_attempt = 2;

    public TaskProcess() throws IOException {
        input = new BufferedReader(new InputStreamReader(System.in) );
        filereader = new FileReader();
        taskStorage = filereader.readFile();
    }

    public ArrayList<Tasks> getTasks() {
        return taskStorage.getTasks();
    }

    public void addNewTask() throws IOException {
        System.out.print("Enter Task Title: ");
        String title = sc.next();
        System.out.print("Enter Project Name: ");
        String project = sc.next();
        System.out.print("Enter Due date(dd-MM-yyyy): ");
        Date dueDate = verifyDueDateFormat();
        String status = "In Progress";

        Tasks tasks = new Tasks(getNewId(), title, project, dueDate, status);
        taskStorage.getTasks().add(tasks);

        for(Tasks task: taskStorage.getTasks())
            System.out.println(getNewId() + "##" + task.getTitle() + "##" + task.getProject() +
                    "##" + task.getDueDate() + "##" + task.getStatus());
    }

    public int getNewId() {
        return taskStorage.getId();
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
        filereader.saveTask(taskStorage);
    }

    public List<Tasks> tasksByProject(final String project) {
        return taskStorage.getTasks().stream().filter(tasks -> project.equals(tasks.getProject())).collect(Collectors.toList());

    }

    public List<Tasks> tasksByDate() {
        Collections.sort(taskStorage.getTasks());
        for(Tasks task: tasksByDate())
            System.out.println(task.getTaskId() + "##" + task.getTitle() + "##" + task.getProject() +
                    "##" + task.getDueDate() + "##" + task.getStatus());
        return taskStorage.getTasks();
    }

    public int getUserOption() throws IOException {
        try {
            return Integer.parseInt(userInput());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public int verifyId() throws IOException {
        boolean found = false;
        int count = 1;
        int id;
        while (count <= max_attempt) {
            taskStorage.getTasks();
            System.out.print("Enter Task(id) for update: ");
            id = getUserOption();
            for (Tasks tasks : taskStorage.getTasks()) {
                if (id == tasks.getTaskId()) {
                    return id;
                }
            }
            if (found == false) {
                System.out.println("Invalid Task Id.");
                count++;
            }
        }
        return -1;
    }

    /**
     * Returns the task corresponding to the given id
     *
     * @param id the id of task to be returned
     * @return task if present in the list otherwise return null
     */
    public Tasks getTaskById(int id) {
        ArrayList<Tasks> tasks = getTasks();
        for (Tasks task : tasks) {
            if (id == task.getTaskId()) {
                return task;
            }
        }
        return null;
    }

    /**
     * Update the title of the task with the given id
     *
     * @param id    the id of the task to be updated with new title
     * @param title the new value of title
     * @return true if found the task with the id otherwise returns false
     */
    public boolean updateTaskTitle(int id, String title) {
        Tasks task = getTaskById(id);
        if (task != null) {
            task.setTitle(title);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Update the project of the task with the given id
     *
     * @param id      the id of the task to be updated with new project name
     * @param project the new value of project name
     * @return true if found the task with the id otherwise returns false
     */
    public boolean updateTaskProject(int id, String project) {
        Tasks task = getTaskById(id);
        if (task != null) {
            task.setProject(project);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Update the due date of the task with the given id
     *
     * @param id      the id of the task to be updated with new due date
     * @param dueDate the new value of due date
     * @return true if found the task with the id otherwise returns false
     */
    public boolean updateTaskDueDate(int id, Date dueDate) {
        Tasks task = getTaskById(id);
        if (task != null) {
            task.setDueDate(dueDate);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Update the status of the task with the given id
     *
     * @param id      the id of the task to be updated with new status
     * @param status the new value of status
     * @return true if found the task with the id otherwise returns false
     */

    public boolean updateTaskDueDate(int id, String status) {
        Tasks task = getTaskById(id);
        if (task != null) {
            task.setStatus(status);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Remove the task corresponding to the selected id
     *
     * @param id of task to be removed
     */
    public void removeTask(int id) {
        Tasks task = getTaskById(id);
        getTasks().remove(task);
    }


}
