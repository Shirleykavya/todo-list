/**
 * This class represents a single task in ToDoApp.
 */

package todo.tasks;

import java.util.Date;
import java.io.Serializable;

public class Tasks implements Serializable, Comparable<Tasks> {

    // The task id
    private int taskId;

    // Title of the task.
    private String title;

    // Task due date.
    private Date dueDate;

    // Project for the task.
    private String project;

    // To check the Status of the task.
    private boolean status;


    public Tasks(int taskId, String title, String project, Date dueDate, boolean status){

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

    public boolean getStatus() {
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

    public void setStatus (boolean status){
        this.status = status;
    }

    public String getDetails() {
        return taskId + "## " + title + "## " + dueDate + "##" + project + "## " + status;
    }

    /**
     * Compare tasks according to the due dates.
     *
     * @param t Tasks object
     * @return an integer value for comparison
     */
    @Override
    public int compareTo(Tasks t) {
        if (getDueDate() == null || t.getDueDate() == null) {
            return 0;
        }
        return getDueDate().compareTo(t.getDueDate());
    }

}

