import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.format.DateTimeParseException;

/**
 * UserInput handles all user interactions through a CLI interface,
 * including adding, viewing, completing, deleting, saving, and loading tasks.
 */
public class UserInput {

    private int taskChoice;
    private int menuChoice;
    private Scanner s;
    private TaskManager manager;
    boolean exit = false;
    private String dateInput;
    //one for current time and one for date that will be in task
    private LocalDate time;
    private LocalDate date;

    //no arg constructor
    UserInput(){

        time = LocalDate.now();
        s = new Scanner(System.in);
        manager = new TaskManager();
        menuChoice = 0;
        taskChoice = 0;
    }

    /**
     * Runs the main interaction loop, prompting the user for actions until exit.
     */
    public void run(){
        //repeated ask for input
        while (!exit) {
            System.out.println("===== Welcome to your Task Manager     Current Date : " + time.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) + " ======");
            System.out.println("1. Create a Task");
            System.out.println("2. View All Tasks");
            System.out.println("3. View Incomplete Tasks");
            System.out.println("4. Complete a Task");
            System.out.println("5. Delete a Task");
            System.out.println("6. Exit and Save the Task");
            System.out.println("7. Load lists");

            //try-catch for handling invalid input
            try {
                menuChoice = s.nextInt();
                switch (menuChoice) {
                    case 1:
                        s.nextLine();
                        System.out.println("Enter Task name: ");
                        String name = s.nextLine();
                        //in case wrong time submitted handle it gracefully
                        while(true){
                            System.out.println("Please enter the due date: (MM/DD/YYYY)");
                            dateInput = s.nextLine();
                            try{
                                //make it into a date
                                date = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                                break; //if valid will break out loop
                            }catch(DateTimeParseException e){
                                System.out.println("Please enter a valid date (eg. 06/19/2029)");
                            }

                        }
                        manager.addTask(new Task(name, false, date));
                        break;
                    case 2:
                        s.nextLine();
                        manager.seeAllTasks();
                        break;
                    case 3:
                        s.nextLine();
                        manager.seeIncompleteTasks();
                        break;
                    case 4:
                        s.nextLine();
                        manager.seeAllTasks();
                        System.out.println("Enter Task number to complete: ");
                        taskChoice = s.nextInt();
                        manager.completeTask(taskChoice);
                        break;
                    case 5:
                        s.nextLine();
                        manager.seeAllTasks();
                        System.out.println("Enter Task number to delete: ");
                        taskChoice = s.nextInt();
                        manager.removeTask(taskChoice);
                        break;
                    case 6:
                        s.nextLine();
                        manager.saveToFile();
                        System.out.println("Thank you for using Task Manager... Goodbye");
                        exit = true;
                        break;
                    case 7:
                        s.nextLine();
                        System.out.println("Loading....");
                        manager.loadFromFile();
                        break;
                    default:
                        System.out.println("Invalid input!");
                }
            }catch (InputMismatchException e) {
                //clear next line from keyboard if invalid input
                s.nextLine();
                System.out.println("Invalid input, Please try again!");
            }

        }

        //close keyboard
        s.close();
    }
}
