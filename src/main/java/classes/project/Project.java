package classes.project;

import classes.exceptions.EmptyTaskListException;
import classes.exceptions.IncorrectProjectNameException;
import classes.exceptions.NegativeRewardException;
import classes.exceptions.NegativeTimeRequiredException;
import classes.interfaces.Clearable;
import classes.interfaces.JSONExternalizable;
import classes.json.JSONManager;
import classes.ui.Input;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Project implements JSONExternalizable, Clearable {
    public static final Logger LOGGER = LogManager.getLogger(Project.class);
    private String projectName;
    private List<Task> taskList;

    public Project(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public String toString() {
        return "Project" +
                " " + projectName + '\n' +
                "Tasks: " + "\n" + this.showTasks();
    }

    public String showTasks() {
        StringBuilder tasks = new StringBuilder();
        int i = 1;
        for (Task task : this.taskList) {
            String marker = (task.getComplete()) ? "☑️\n" : "❌\n";
            tasks.append(i).append(". ").append(
                            task.getTaskName()).append(" | Time required: ")
                    .append(task.getTimeRequired())
                    .append(" hours | Reward: ")
                    .append(task.getReward())
                    .append(marker);
            i++;
        }
        return tasks.toString();
    }

    // Function for adding tasks to the current project
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    // and for removing tasks
    public void removeTask(Task task) throws EmptyTaskListException {
        if (this.taskList.isEmpty()) {
            throw new EmptyTaskListException("Cannot remove tasks from an empty list!");
        }
        this.taskList.remove(task);
    }

    public void removeTask(String taskName) {
        if (this.getTask(taskName) != null) {
            this.taskList.remove(this.getTask(taskName));
        } else {
            LOGGER.info("No such task.");
        }
    }

    public void addTaskFromInput() {
        LOGGER.info("Enter new task name (cannot be blank):");
        String taskName = "";
        while (taskName.isBlank()) {
            taskName = Input.stringConsoleInput();
        }
        int timeRequired = 0;
        while (timeRequired <= 0) {
            try {
                LOGGER.info("Enter time required:");
                timeRequired = Integer.parseInt(Input.stringConsoleInput());
                if (timeRequired <= 0) {
                    throw new NegativeTimeRequiredException("Negative or zero time set for a task.");
                }
            } catch (NumberFormatException e) {
                LOGGER.info("Not an integer. Try again?");
            } catch (NegativeTimeRequiredException e) {
                LOGGER.info("Tasks must have positive non-zero time requirement!");
            }
        }
        LOGGER.info("Enter reward:");
        BigDecimal reward = null;
        while (reward == null) {
            try {
                reward = new BigDecimal(Input.stringConsoleInput());
                if (reward.compareTo(BigDecimal.valueOf(0)) < 0) {
                    throw new NegativeRewardException("Reward must be non-negative");
                }
            } catch (NumberFormatException e) {
                LOGGER.info("Incorrect format. Try separating mantissa with a dot?");
            } catch (NegativeRewardException e) {
                reward = null;
                LOGGER.info("Reward cannot be negative");
            }
        }
        this.addTask(new Task(taskName, this, timeRequired, reward));
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public Task getTask(String taskName) {
        for (Task task : taskList) {
            if (Objects.equals(task.getTaskName(), taskName)) {
                return task;
            }
        }
        return null;
    }

    public HashMap<String, Boolean> getToDoList() {
        HashMap<String, Boolean> toDoMap = new HashMap<>();
        for (Task task : this.getTaskList()) {
            toDoMap.put(task.getTaskName(), task.getComplete());
        }
        return toDoMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(projectName, project.projectName) && Objects.equals(taskList, project.taskList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectName, taskList);
    }

    @Override
    public boolean writeJSON() {
        try {
            return JSONManager.saveProject(this);
        } catch (IncorrectProjectNameException e) {
            while (true) {
                LOGGER.info("Project name contains whitespace and will be saved using '_'");
                LOGGER.info("Choose different filename? (y/n)");
                String response = Input.stringConsoleInput().toLowerCase();
                if (response.equals("y")) {
                    LOGGER.info("Enter new filename (in full): ");
                    return JSONManager.saveProject(this, Input.stringConsoleInput(true));
                } else if (response.equals("n")) {
                    return JSONManager.saveProject(this, true);
                }
            }
        }
    }

    @Override
    public Project readJSON(String filename) {
        return JSONManager.loadProject(filename);
    }

    @Override
    public void clear() {
        this.getTaskList().clear();
    }
}