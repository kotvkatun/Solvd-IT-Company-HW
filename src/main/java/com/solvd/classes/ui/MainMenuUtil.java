package com.solvd.classes.ui;

import com.solvd.classes.developer.Developer;
import com.solvd.classes.exceptions.EmptyTaskListException;
import com.solvd.classes.itcompany.ITCompany;
import com.solvd.classes.json.JSONManager;
import com.solvd.classes.project.CoolLinkedList;
import com.solvd.classes.project.Project;
import com.solvd.classes.project.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class MainMenuUtil {
    private static final Logger LOGGER = LogManager.getLogger(MainMenuUtil.class);
    public static int monthsPassed = 0;

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
            MainMenuUtil.developerInfo(developerList);
            LOGGER.info("Enter developer's index:");
            int devIndex = Integer.parseInt(Input.stringConsoleInput()) - 1;
            MainMenuUtil.projectInfo(project);
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
                int taskIndex = Integer.parseInt(Input.stringConsoleInput()) - 1;
                project.removeTask(taskIndex);
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
        itCompany.refreshDeveloperTime();
        MainMenuUtil.monthsPassed++;
    }

    public static void toDoList(HashMap<String, Boolean> toDoMap) {
        LOGGER.info("Tasks to do: ");
        for (String taskName : toDoMap.keySet()) {
            String marker = (toDoMap.get(taskName)) ? " ✅" : " ❌";
            LOGGER.info(taskName + marker);
        }
    }

    public static void filterTaskList(CoolLinkedList<Task> taskList) {
        List<Task> tasks = new ArrayList<>();
        for (Task task : taskList) {
            tasks.add(task);
        }
        List<Task> filteredTasks = tasks.stream()
                .filter(Input.constructPredicateFromInput())
                .toList();
        for (Task task : filteredTasks) {
            LOGGER.info(task.toString());
        }
    }

    public static void undupe(CoolLinkedList<Task> taskCoolLinkedList) {
        taskCoolLinkedList.clearDupes();
    }
}