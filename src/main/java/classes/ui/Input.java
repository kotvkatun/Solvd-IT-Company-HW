package classes.ui;

import classes.project.Project;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Input {
    public static final Logger LOGGER = LogManager.getLogger(Input.class);
    // Method for taking user input
    public static String stringConsoleInput() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        // Try to scan something into the string until something is found
        while (input.isBlank()) {
            try {
                input = scanner.nextLine().toLowerCase();
            } catch (NoSuchElementException e) {
                LOGGER.info("Incorrect input. Please try again");
            }
        }
        return input;
    }
    public static String stringConsoleInput(boolean tryUntilAllowedAsFilename) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        // Try to scan something into the string until it fits filename regex
        while (true) {
            try {
                input = scanner.nextLine();
            } catch (NoSuchElementException e) {
                LOGGER.info("Incorrect input. Please try again");
                continue;
            }
            if (!input.matches("^[\\w-]+\\.[A-Za-z]{4}$")) {
                LOGGER.info("Please enter a valid filename");
            } else {
                break;
            }
        }
        return input;
    }
}