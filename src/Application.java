import java.io.IOException;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        TaskManager taskManager = new TaskManager();
        Scanner sc = new Scanner(System.in);
        int choice;
        //do {

            taskManager.welcome();
            System.out.print("Select Option: ");
            choice = taskManager.getUserOption();
            taskManager.process(choice);
        //}while (choice != 4);

    }

}
