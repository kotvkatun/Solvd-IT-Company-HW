package classes.interfaces;

import classes.project.Project;
import classes.project.Task;
import org.json.JSONArray;
import org.json.JSONObject;

public interface IGetJSONObject {
    static JSONObject getJSONObject(Project project) {
        JSONObject projectJSON = new JSONObject();
        projectJSON.put("projectName", project.getProjectName());
        JSONArray tasksJSONArray = new JSONArray();
        for (Task task: project.getTaskList()) {
            JSONObject taskJSONObject = new JSONObject();
            taskJSONObject.put("taskName", task.getTaskName());
            taskJSONObject.put("timeRequired", task.getTimeRequired());
            taskJSONObject.put("reward", task.getReward().toString());
            tasksJSONArray.put(taskJSONObject);
        }
        projectJSON.put("tasks", tasksJSONArray);
        return projectJSON;
    }
}
