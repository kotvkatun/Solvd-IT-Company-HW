package com.solvd.classes.json;

public enum JSONFields {
    PROJECT_NAME("projectName"),
    TASK_NAME("taskName"),
    TIME_REQUIRED("timeRequired"),
    REWARD("reward"),
    TASKS("tasks");
    private final String fieldName;

    JSONFields(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
