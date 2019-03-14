package todo.tasks;

import java.util.Date;

public class Tasks implements Comparable<Tasks> {

    // The task id
    private int taskId;

    // Title of the task.
    private String title;

    // Task due date.
    private Date dueDate;

    // Project for the task.
    private String project;

    // To check the Status of the task.
    private String status;


    public Tasks(int taskId, String title, String project, Date dueDate, String status){

        this.taskId = taskId;
        this.title = title;
        this.project = project;
        this.dueDate = dueDate;
        this.status = status;
    }

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

    public String getStatus() {
        return status;
    }

	 public void setTaskId() {
	       this.taskId = taskId;
	   }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public void setStatus (String status){
        this.status = status;
    }

    public String getDetails() {
        return taskId + "## " + title + "## " + dueDate + "##" + project + "## " + status;
    }

    @Override
    public int compareTo(Tasks t) {
        if (getDueDate() == null || t.getDueDate() == null) {
            return 0;
        }
        return getDueDate().compareTo(t.getDueDate());
    }



}
