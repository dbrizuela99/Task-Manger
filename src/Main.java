//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    // Main.java

    /**
     * Entry point for the Task Manager CLI Application.
     * Delegates all user interactions to the UserInput class.
     */

    public static void main(String[] args) {
        UserInput userInput = new UserInput();
        userInput.run();
    }
}
