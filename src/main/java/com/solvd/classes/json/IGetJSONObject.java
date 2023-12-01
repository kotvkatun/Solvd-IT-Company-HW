package com.solvd.classes.json;

import com.solvd.classes.project.Project;
import com.solvd.classes.project.Task;
import org.json.JSONArray;
import org.json.JSONObject;

import static com.solvd.classes.json.JSONFields.*;

public interface IGetJSONObject {
    static JSONObject getJSONObject(Project project) {
        JSONObject projectJSON = new JSONObject();
        projectJSON.put(PROJECT_NAME.getFieldName(), project.getProjectName());
        JSONArray tasksJSONArray = new JSONArray();
        for (Task task : project.getTaskList()) {
            JSONObject taskJSONObject = new JSONObject();
            taskJSONObject.put(TASK_NAME.getFieldName(), task.getTaskName());
            taskJSONObject.put(TIME_REQUIRED.getFieldName(), task.getTimeRequired());
            taskJSONObject.put(REWARD.getFieldName(), task.getReward().toString());
            tasksJSONArray.put(taskJSONObject);
        }
        projectJSON.put(TASKS.getFieldName(), tasksJSONArray);
        return projectJSON;
    }
}
