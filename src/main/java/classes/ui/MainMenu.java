package classes.ui;

import classes.developer.Developer;

import java.util.List;

public class MainMenu {
    public static void showMenu() {
        System.out.println("""
                ----------------------------------
                 Available commands:             \s
                 1. project - Show project info\s
                 2. developer - Show developer info\s
                 3. assign - Assign tasks to developers\s
                 4. manage - Add or remove tasks from a project\s
                 5. open - Load an existing project from a file\s
                 exit - Exit application""");
    }
    public static void developerInfo(List<Developer> developerList) {
        int i = 1;
        for(Developer dev : developerList) {
            System.out.println(i + dev.toString());
            i++;
        }
    }
}