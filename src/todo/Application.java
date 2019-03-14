package todo;

import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        TaskManager taskManager = new TaskManager();
        taskManager.welcome();
        taskManager.process();
    }

}
