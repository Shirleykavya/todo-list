import java.text.DateFormat;
import java.util.Date;
import java.util.*;
import java.text.*;

public class Tasks {

    // The task id
    private int taskId;

    // Title of the task.
    private String title;

    // Task due date.
    private Date dueDate;

    // Project for the task.
    private String project;

    // To check the Status of the task.
    private String taskStatus;

    private int indexId = 1;

/*
    public Tasks(int taskId, String title, Date dueDate, String project, String taskStatus){

        this.taskId = taskId;
        this.title = title;
        this.dueDate = dueDate;
        this.project = project;
        this.taskStatus = taskStatus;

    }
*/


    public int getTaskId() {

        return taskId;
    }

    public String getTitle() {
        return title;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public String getProject() {
        return project;
    }

    public String getTaskStatus() {

        return taskStatus;
    }

    public void setTaskId() {
        this.taskId = indexId++;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setProject(String project) {
        this.project = project;;
    }

    public void setTaskStatus (){
        this.taskStatus = "In Progress";
    }

}
