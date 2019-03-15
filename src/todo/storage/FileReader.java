/**
 * This class represents to store tasklist in a file and to read a file
 */

package todo.storage;

import todo.tasks.TaskStorage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileReader {

    /**
     * Save task list to the file
     *
     * @param taskStore the list of tasks
     * @throws IOException
     */
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

    /**
     * Retrieve data from file
     *
     * @return the list of file
     */
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
