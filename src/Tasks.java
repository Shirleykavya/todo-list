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

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Scanner sc = new Scanner(System.in);


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
