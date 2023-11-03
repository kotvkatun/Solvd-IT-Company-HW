package classes;

import classes.developer.Developer;
import classes.developer.Grade;
import classes.json.JSONLoader;
import classes.project.Project;
import classes.ui.Input;
import classes.ui.MainMenu;


import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Developer junVasya = new Developer(Grade.JUNIOR, "Vasya Pupkin");
        Developer midPetya = new Developer(Grade.MIDDLE, "Petya Petrov");
        Developer senPomidor = new Developer(Grade.SENIOR, "Senior Pomidor");
        List<Developer> developerList = new ArrayList<>();
        developerList.add(junVasya);
        developerList.add(midPetya);
        developerList.add(senPomidor);
        Project project = JSONLoader.loadProject("project1");
        if (project == null) {
            System.out.println("Can't find default project.");
            System.exit(1);
        }
        while (true) {
            MainMenu.showMenu();
            String command = Input.consoleInput();
            switch (command) {
                case "project":
                    System.out.println(project.toString());
                    break;
                case "developer":
                    MainMenu.developerInfo(developerList);
                    break;
                case "assign":
                    try {
                        System.out.println("Enter developer's index:");
                        int devIndex = Integer.parseInt(Input.consoleInput()) - 1;
                        System.out.println("Enter task index:");
                        int taskIndex = Integer.parseInt(Input.consoleInput()) - 1;
                        developerList.get(devIndex).completeTask(project.getTaskList().get(taskIndex));
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Whoops, can't parse that!");
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Incorrect index or task does not exist");
                        break;
                    }
                case "manage":
                    System.out.println("Add or Remove task from project?");
                    String addOrRemove = Input.consoleInput();
                    if (addOrRemove.equals("Add")) {
                        project.addTaskFromInput();
                        break;
                    } else if (addOrRemove.equals("Remove")) {
                        System.out.println("Enter task index:");
                        int taskIndex = Integer.parseInt(Input.consoleInput());
                        try {
                            project.removeTask(project.getTaskList().get(taskIndex));
                            System.out.println("Removed task " + taskIndex);
                            break;
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Incorrect index");
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Whoops, can't parse that!");
                            break;
                        }
                    } else {
                        System.out.println("Type Add or Remove to change tasks.");
                        break;
                    }
                case "open":
                    System.out.println("Enter filename (without extension): ");
                    String filename = Input.consoleInput();
                    project = JSONLoader.loadProject(filename);
                    if (project == null) {
                        System.out.println("Cannot load project from specified filename");
                        break;
                    }
                    System.out.println("Loaded project " + project.getProjectName() + " from " + filename);
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
