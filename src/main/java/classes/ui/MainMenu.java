package classes.ui;

import classes.developer.Developer;
import classes.itcompany.ITCompany;
import classes.json.JSONManager;
import classes.project.Project;
import classes.project.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class MainMenu {
    private static final Logger LOGGER = LogManager.getLogger(MainMenu.class);
    public static void showMenu() {
        LOGGER.info("""
                ----------------------------------
                 Available commands:             \s
                 - project - Show project info\s
                 - developer - Show developer info\s
                 - assign - Assign tasks to developers\s
                 - manage - Add or remove tasks from a project\s
                 - open - Load an existing project from a file\s
                 - save - Save current project to a file (will keep project's name)\s
                 exit - Exit application""");
    }
    public static void projectInfo(Project project){
        if (project == null) {
            LOGGER.info("No project loaded. Please use 'open' to open a new project.");
            return;
        }
        LOGGER.info(project.toString());
    }
    public static void developerInfo(List<Developer> developerList) {
        int i = 1;
        for(Developer dev : developerList) {
            LOGGER.info(i + ". " + dev.toString());
            i++;
        }
    }
    public static void assign(List<Developer> developerList, List<Task> taskList) {
        if (taskList == null) {
            LOGGER.info("No project loaded. Please use 'open' to open a new project.");
            return;
        }
        try {
            LOGGER.info("Enter developer's index:");
            int devIndex = Integer.parseInt(Input.stringConsoleInput()) - 1;
            LOGGER.info("Enter task index:");
            int taskIndex = Integer.parseInt(Input.stringConsoleInput()) - 1;
            developerList.get(devIndex).completeTask(taskList.get(taskIndex));
        } catch (NumberFormatException e) {
            LOGGER.info("Whoops, can't parse that!");
        } catch (IndexOutOfBoundsException e) {
            LOGGER.info("Incorrect index or task does not exist");
        }
    }
    public static void manage(Project project) {
        if (project == null) {
            LOGGER.info("No project loaded. Please use 'open' to open a new project.");
            return;
        }
        LOGGER.info("Add or remove task from project?");
        String addOrRemove = Input.stringConsoleInput();
        if (addOrRemove.equals("add")) {
            project.addTaskFromInput();
        } else if (addOrRemove.equals("remove")) {
            LOGGER.info(project);
            LOGGER.info("Enter task index:");
            try {
                int taskIndex = Integer.parseInt(Input.stringConsoleInput());
                project.removeTask(project.getTaskList().get(taskIndex));
                LOGGER.info("Removed task " + taskIndex);
            } catch (IndexOutOfBoundsException e) {
                LOGGER.info("Incorrect index");
            } catch (NumberFormatException e) {
                LOGGER.info("Whoops, can't parse that!");
            }
        } else {
            LOGGER.info("Type Add or Remove to change tasks.");
        }
    }
    public static void open(ITCompany itCompany){
        LOGGER.info("Enter filename (without extension): ");
        String filename = Input.stringConsoleInput();
        itCompany.setProject(JSONManager.loadProject(filename));
        if (itCompany.getProject() == null) {
            LOGGER.info("Cannot load project from specified filename");
            return;
        }
        LOGGER.info("Loaded project " + itCompany.getProject() + " from " + filename);
    }
    public static void save(Project project) {
        try {
            if (project.writeJSON()) {
                LOGGER.info("Saved project to " + project.getProjectName() + ".json");
            } else {
                LOGGER.info("Saving aborted.");
            }
        } catch (NullPointerException e) {
            LOGGER.info("No project opened. Try opening one?");
        }
    }
}