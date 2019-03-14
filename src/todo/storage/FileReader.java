package todo.storage;

import todo.tasks.TaskStorage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileReader {

    private final static String path = "Taskfile.txt";
    //public TaskStorage taskstorage;

    public void saveTask(TaskStorage taskstorage) throws IOException {
        try {
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream( fos );
            oos.writeObject(taskstorage);
            oos.close();
        } catch (Exception e) {
            System.out.println("Fail to write");
            e.printStackTrace();
        }
    }

    public TaskStorage readFile() throws IOException{

        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream( fis );
            return (TaskStorage) ois.readObject();
            //ois.close();
        }
        catch (Exception e) {
            System.out.println("Fail to read");
            //e.printStackTrace();
            return new TaskStorage();
        }
    }

}
