package classes.json;

import classes.project.Project;
import classes.project.Task;
import org.apache.commons.io.IOUtils;
import org.json.*;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public final class JSONManager {
    public static Project loadProject(String filename) {
        // Read json into string
        String projectstring;
        try(FileInputStream inputStream = new FileInputStream(filename + ".json")){
            projectstring = IOUtils.toString(inputStream);
        } catch (IOException e) {
            System.out.println("File not found. Try again?");
            return null;
        }
        // Create JSONobject from said string
        JSONObject projectJSON = new JSONObject(projectstring);
        // Get tasks object array from json
        JSONArray tasksJSONarray = projectJSON.getJSONArray("tasks");
        // Set up variables for Project
        Project project = new Project(projectJSON.getString("projectName"));
        List<Task> taskList = new ArrayList<>();
        // Iterate over JSONarray to get individual tasks
        for (Object taskObj: tasksJSONarray) {
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
    public static boolean saveProject(Project project) {
        JSONObject projectJSON = new JSONObject();
        projectJSON.put("projectName", project.getProjectName());
        JSONArray tasksJSONArray = new JSONArray();
        for (Task task: project.getTaskList()) {
            JSONObject taskJSONObject = new JSONObject();
            taskJSONObject.put("taskName", task.getTaskName());
            taskJSONObject.put("timeRequired", task.getTimeRequired());
            taskJSONObject.put("reward", task.getReward());
            tasksJSONArray.put(taskJSONObject);
        }
        projectJSON.put("tasks", tasksJSONArray);
        try (FileWriter fileWriter = new FileWriter(project.getProjectName())) {
            fileWriter.write(projectJSON.toString(4));
            return true;
        } catch (IOException e) {
            System.out.println("Cannot write to file. Check file/project name and try again.");
            return false;
        }
    }
}