package classes.json;

import classes.project.Project;
import classes.project.Task;
import org.apache.commons.io.IOUtils;
import org.json.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class JSONLoader {
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
}