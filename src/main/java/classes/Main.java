package classes;

import classes.developer.Developer;
import classes.initialiser.DeveloperInitialiser;
import classes.json.JSONLoader;
import classes.project.Project;
import classes.ui.Input;
import classes.ui.MainMenu;


import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Developer> developerList = DeveloperInitialiser.initialiseDeveloperList();
        Project project = JSONLoader.loadProject("project1");
        if (project == null) {
            System.out.println("Can't find default project.");
            System.exit(1);
        }
        while (true) {
            MainMenu.showMenu();
            String command = Input.stringConsoleInput();
            switch (command) {
                case "project":
                    System.out.println(project.toString());
                    break;
                case "developer":
                    MainMenu.developerInfo(developerList);
                    break;
                case "assign":
                    MainMenu.assign(developerList, project.getTaskList());
                    break;
                case "manage":
                    MainMenu.manage(project);
                    break;
                case "open":
                    MainMenu.open(project);
                    break;
                case "exit":
                    System.exit(0);
                default:
                    System.out.println("Wrong command.");
                    break;
            }
        }
    }
}
