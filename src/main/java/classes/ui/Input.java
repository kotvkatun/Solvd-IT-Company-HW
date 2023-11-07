package classes.ui;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Input {
    // Method for taking user input
    public static String stringConsoleInput() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        // Try to scan something into the string until something is found
        while (input.isEmpty()) {
            try {
                input = scanner.nextLine().toLowerCase();
            } catch (NoSuchElementException e) {
                System.out.println("Incorrect input. Please try again");
            }
        }
        return input;
    }
}