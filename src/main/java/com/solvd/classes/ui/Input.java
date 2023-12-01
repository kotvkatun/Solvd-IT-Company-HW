package com.solvd.classes.ui;

import com.solvd.classes.project.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Predicate;

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
        String input;
        // Try to scan something into the string until it fits filename regex
        while (true) {
            input = Input.stringConsoleInput();
            if (!input.matches("^[\\w-]+\\.[A-Za-z]{4}$")) {
                LOGGER.info("Please enter a valid filename");
            } else {
                break;
            }
        }
        return input;
    }

    public static MenuOptions menuOptionConsoleInput() {
        MenuOptions input;
        while (true) {
            try {
                input = MenuOptions.valueOf(Input.stringConsoleInput().toUpperCase().strip());
                return input;
            } catch (IllegalArgumentException e) {
                LOGGER.info("Wrong command.");
            }
        }
    }

    public static Predicate<? super Task> constructPredicateFromInput() {
        String operator;
        String secondParameter;
        while (true) {
            LOGGER.info("Enter filtering operation: >, < or =");
            operator = Input.stringConsoleInput();
            if (!List.of(">", "<", "=").contains(operator)) {
                LOGGER.info("Specified operator is not supported.");
                continue;
            }
            break;
        }
        while (true) {
            LOGGER.info("Enter second term: ");
            secondParameter = Input.stringConsoleInput();
            if (!secondParameter.matches("[0-9]*.[0-9]*")) {
                LOGGER.info("Input not numeric.");
                continue;
            }
            break;
        }
        return getPredicate(secondParameter, operator);
    }

    private static Predicate<? super Task> getPredicate(String secondParameter, String operator) {
        BigDecimal secondParam = new BigDecimal(secondParameter);
        Predicate<? super Task> predicate = task -> true;
        switch (operator) {
            case ">" -> predicate = t -> (t.getReward().compareTo(secondParam) > 0);
            case "=" -> predicate = t -> (t.getReward().compareTo(secondParam) == 0);
            case "<" -> predicate = t -> (t.getReward().compareTo(secondParam) < 0);
        }
        return predicate;
    }
}