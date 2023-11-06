package classes.ui;

import classes.developer.Developer;
import classes.json.JSONManager;
import classes.project.Project;
import classes.project.Task;

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
            System.out.println(i + ". " + dev.toString());
            i++;
        }
    }
    public static void assign(List<Developer> developerList, List<Task> taskList) {
        try {
            System.out.println("Enter developer's index:");
            int devIndex = Integer.parseInt(Input.stringConsoleInput()) - 1;
            System.out.println("Enter task index:");
            int taskIndex = Integer.parseInt(Input.stringConsoleInput()) - 1;
            developerList.get(devIndex).completeTask(taskList.get(taskIndex));
        } catch (NumberFormatException e) {
            System.out.println("Whoops, can't parse that!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Incorrect index or task does not exist");
        }
    }
    public static void manage(Project project) {
        System.out.println("Add or remove task from project?");
        String addOrRemove = Input.stringConsoleInput();
        if (addOrRemove.equals("add")) {
            project.addTaskFromInput();
        } else if (addOrRemove.equals("remove")) {
            System.out.println("Enter task index:");
            try {
                int taskIndex = Integer.parseInt(Input.stringConsoleInput());
                project.removeTask(project.getTaskList().get(taskIndex));
                System.out.println("Removed task " + taskIndex);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Incorrect index");
            } catch (NumberFormatException e) {
                System.out.println("Whoops, can't parse that!");
            }
        } else {
            System.out.println("Type Add or Remove to change tasks.");
        }
    }
    public static void open(Project project){
        System.out.println("Enter filename (without extension): ");
        String filename = Input.stringConsoleInput();
        project = JSONManager.loadProject(filename);
        if (project == null) {
            System.out.println("Cannot load project from specified filename");
        }
        System.out.println("Loaded project " + project.getProjectName() + " from " + filename);
    }
}