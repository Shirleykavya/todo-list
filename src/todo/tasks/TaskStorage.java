package todo.tasks;
import java.util.ArrayList;
import java.io.Serializable;

public class TaskStorage implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private ArrayList<Tasks>tasks;

    public TaskStorage() {

    }

    public ArrayList<Tasks> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Tasks> tasks) {
        this.tasks = tasks;
    }
}
