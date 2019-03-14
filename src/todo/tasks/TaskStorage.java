package todo.tasks;
import java.util.ArrayList;
import java.io.Serializable;

public class TaskStorage implements Serializable {

    private ArrayList<Tasks>tasks;
    private int id;

    public TaskStorage() {
        tasks = new ArrayList<>();
        id = 0;
    }

    public ArrayList<Tasks> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Tasks> tasks) {
        this.tasks = tasks;
    }

    public int getId(){
        return id++;
    }

    public void setId(int id){
        this.id = id;
    }


}
