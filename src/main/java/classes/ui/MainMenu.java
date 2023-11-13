package classes.ui;

import classes.developer.Developer;
import classes.exceptions.EmptyTaskListException;
import classes.itcompany.ITCompany;
import classes.json.JSONManager;
import classes.project.Project;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;

public final class MainMenu {
    private static final Logger LOGGER = LogManager.getLogger(MainMenu.class);
    private static int monthsPassed = 0;

    public static void showMenu() {
        LOGGER.info("Months passed: " + MainMenu.monthsPassed);
        LOGGER.info("""
                ----------------------------------
                 Available commands:             \s
                 📒 project - Show project info\s
                 👨🏻‍💻 developer - Show developer info\s
                 👉🏻 assign - Assign tasks to developers\s
                 🛠 manage - Add or remove tasks from a project\s
                 🧹 clear - Remove all current tasks from selected project\s
                 📜 todo - Get a list of tasks to do.\s
                 🗓 next - Set time to next month and refresh developers time limits.\s
                 💽 open - Load an existing project from a file\s
                 💾 save - Save current project to a file (will keep project name)\s
                 exit - Exit application""");
    }

    public static boolean projectIsNull(Project project) {
        if (project == null) {
            LOGGER.info("No project loaded. Please use 'open' to open a new project.");
            return true;
        }
        return false;
    }

    public static void projectInfo(Project project) {
        if (projectIsNull(project)) {
            return;
        }
        LOGGER.info(project.toString());
    }

    public static void developerInfo(List<Developer> developerList) {
        int i = 1;
        for (Developer dev : developerList) {
            LOGGER.info(i + ". " + dev.toString());
            i++;
        }
    }

    public static void assign(List<Developer> developerList, Project project) {
        if (projectIsNull(project)) {
            return;
        }
        try {
            MainMenu.developerInfo(developerList);
            LOGGER.info("Enter developer's index:");
            int devIndex = Integer.parseInt(Input.stringConsoleInput()) - 1;
            MainMenu.projectInfo(project);
            LOGGER.info("Enter task index:");
            int taskIndex = Integer.parseInt(Input.stringConsoleInput()) - 1;
            developerList.get(devIndex).completeTask(project.getTaskList().get(taskIndex));
        } catch (NumberFormatException e) {
            LOGGER.info("Whoops, can't parse that!");
        } catch (IndexOutOfBoundsException e) {
            LOGGER.info("Incorrect index or task does not exist");
        }
    }

    public static void manage(Project project) {
        if (projectIsNull(project)) {
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
            } catch (EmptyTaskListException e) {
                LOGGER.info("Task list is empty, nothing to remove");
            }
        } else {
            LOGGER.info("Type Add or Remove to change tasks.");
        }
    }

    public static void open(ITCompany itCompany) {
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
                LOGGER.info("Saved project successfully.");
            } else {
                LOGGER.info("Saving aborted.");
            }
        } catch (NullPointerException e) {
            LOGGER.info("No project opened. Try opening one?");
        }
    }

    public static void clearProject(Project project) {
        if (projectIsNull(project)) {
            return;
        }
        project.clear();
        LOGGER.info("Project " + project.getProjectName() + " cleared successfully.");
    }

    public static void nextMonth(ITCompany itCompany) {
        itCompany.switchToNextMonth();
        MainMenu.monthsPassed++;
        MainMenu.showMenu();
    }

    public static void toDoList(HashMap<String, Boolean> toDoMap) {
        LOGGER.info("Tasks to do: ");
        for (String taskName : toDoMap.keySet()) {
            String marker = (toDoMap.get(taskName)) ? " ✅" : " ❌";
            LOGGER.info(taskName + marker);
        }
    }
}