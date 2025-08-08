import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the list of tasks, including adding, removing, printing, and file I/O.
 */
public class TaskManager {

    private List<Task> tasks;

    TaskManager() {
        tasks = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(int i) {
        if (i > tasks.size() || i < 1) {
            System.out.println("Please enter a number between 1 and " + (tasks.size()));
            return;
        }
        System.out.println("Removing " + tasks.get(i - 1).getName());
        tasks.remove(i -1);
    }

    public void completeTask(int i){
        if (i > tasks.size() || i < 1) {
            System.out.println("Please enter a number between 1 and " + (tasks.size()));
            return;
        }
        tasks.get(i-1).completeTask();
        System.out.println("Task: " + tasks.get(i-1).getName() + " has been completed");
    }

    public String seeAllTasks() {
        StringBuilder SB = new StringBuilder();
        if(tasks.isEmpty()) {
           return "There are no tasks in the system";
        }
        else {

            int i = 1;
            SB.append("===== All Tasks =====\n");
            for (Task task : tasks) {
               SB.append(i + "." + task +"\n");
                i++;
            }
        }
        System.out.println(SB.toString());
        return SB.toString();
    }

    public String seeIncompleteTasks() {
        StringBuilder SB = new StringBuilder();
        int i = 1;
       SB.append("===== Task to complete =====\n");
        for (Task task : tasks) {
            if(!task.isComplete()){
                SB.append( i + "." + task + "\n");
                i++;
            }
        }
        System.out.println(SB.toString());
        return SB.toString();
    }

    public void saveToFile(){
        try{
            FileWriter writer = new FileWriter("tasks.txt");
            for(Task task : tasks){
                writer.write(task.getName()+ "," + task.isComplete() + "," + task.getTime() + "\n");
            }
            System.out.println("Tasks have been saved");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to file");
            e.printStackTrace();
        }
    }

    public void loadFromFile(){
        //clear the tasks already there
        tasks.clear();
        try{
            BufferedReader reader = new BufferedReader(new FileReader("tasks.txt"));
            String line;

            while((line = reader.readLine()) != null){
                String[] lineArray = line.split(",");
                // Convert the string date back into a LocalDate using the same format
                LocalDate date = LocalDate.parse(lineArray[2], DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                this.addTask(new Task(lineArray[0], Boolean.parseBoolean(lineArray[1]), date));
            }

            System.out.println("Tasks have been loaded");
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
