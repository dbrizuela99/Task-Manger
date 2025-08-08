import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Task {

    private String name;
    private boolean complete;
    private LocalDate time;

    //constructor to create a task with name and complete status with due dates
    Task(String name,  boolean complete, LocalDate time){
        this.name = name;
        this.complete = complete;
        this.time = time;
    }

    //getters
    public String getName() {
        return name;
    }

    public boolean isComplete() {

        return complete;
    }

    public String getTime() {
        return time.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

    //setter

    public void completeTask(){
        complete = true;
    }

    public String completionStatus()
    {

        return complete ? "Complete" : "Not Complete";
    }

    @Override
    public String toString(){

        return this.name + " -> Completion status: " + this.completionStatus() + " -> Due time: " + this.time.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

}
