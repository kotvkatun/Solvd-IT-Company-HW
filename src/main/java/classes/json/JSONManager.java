package classes.json;

import classes.exceptions.IncorrectJSONFormatException;
import classes.exceptions.IncorrectProjectNameException;
import classes.interfaces.IGetJSONObject;
import classes.project.Project;
import classes.project.Task;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.*;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public final class JSONManager implements IGetJSONObject {
    private static final Logger LOGGER = LogManager.getLogger(JSONManager.class);
    public static Project loadProject(String filename) {
        // Read json into string
        String projectstring;
        try (FileInputStream inputStream = new FileInputStream(filename + ".json")) {
            projectstring = IOUtils.toString(inputStream);
        } catch (IOException e) {
            LOGGER.info("File not found. Try again?");
            return null;
        }
        // Create JSONobject from said string
        JSONObject projectJSON;
        try {
            projectJSON = new JSONObject(projectstring);
        } catch (IncorrectJSONFormatException e) {
            LOGGER.info("Incorrect json formatting");
            LOGGER.error(e.getMessage());
            throw e;
        }
        // Get tasks object array from json
        JSONArray tasksJSONarray = projectJSON.getJSONArray("tasks");
        // Set up variables for Project
        Project project = new Project(projectJSON.getString("projectName"));
        List<Task> taskList = new ArrayList<>();
        // Iterate over JSONarray to get individual tasks
        for (Object taskObj : tasksJSONarray) {
            JSONObject taskJSON = (JSONObject) taskObj;
            // Slot new tasks into the list
            taskList.add(new Task(
                    taskJSON.getString("taskName"),
                    project,
                    taskJSON.getInt("timeRequired"),
                    new BigDecimal(taskJSON.getString("reward"))));
        }
        project.setTaskList(taskList);
        return project;
    }
    public static boolean saveProject(Project project) throws IncorrectProjectNameException {
        if (project == null) {
            LOGGER.info("Can't save an unopened project!");
            return false;
        }
        JSONObject projectJSON = IGetJSONObject.getJSONObject(project);
        if (project.getProjectName().matches(".*\\s.*")) {
            throw new IncorrectProjectNameException("Project name cannot contain whitespace.");
        }
        try (FileWriter fileWriter = new FileWriter(project.getProjectName() + ".json")) {
            fileWriter.write(projectJSON.toString(2));
            return true;
        } catch (IOException e) {
            LOGGER.info("Cannot write to file. Check file/project name and try again.");
            return false;
        }
    }
    public static boolean saveProject(Project project, String filename) {
        if (project == null) {
            LOGGER.info("Can't save an unopened project!");
            return false;
        }
        JSONObject projectJSON = IGetJSONObject.getJSONObject(project);
        try (FileWriter fileWriter = new FileWriter(filename)) {
            fileWriter.write(projectJSON.toString(2));
            return true;
        } catch (IOException e) {
            LOGGER.info("Cannot write to file. Check file/project name and try again.");
            return false;
        }
    }
    public static boolean saveProject(Project project, boolean removeWhitespace) {
        if (project == null) {
            LOGGER.info("Can't save an unopened project!");
            return false;
        }
        JSONObject projectJSON = IGetJSONObject.getJSONObject(project);
        try (FileWriter fileWriter = new FileWriter(project.getProjectName().replace(" ", "_") + ".json")) {
            fileWriter.write(projectJSON.toString(2));
            return true;
        } catch (IOException e) {
            LOGGER.info("Cannot write to file. Check file/project name and try again.");
            return false;
        }
    }
}