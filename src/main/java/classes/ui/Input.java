package classes.ui;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Input {
    protected static final Scanner scanner = new Scanner(System.in);
    // Method for taking user input
    public static String consoleInput() {
        String input = "";
        // Try to scan something into the string until something is found
        while (input.isEmpty()) {
            try {
                input = scanner.nextLine();
            } catch (NoSuchElementException e) {
                System.out.println("Incorrect input. Please try again");
            }
        }
        return input;
    }
}