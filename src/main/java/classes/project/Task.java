package classes.project;

import java.math.BigDecimal;
import java.util.Objects;

public class Task {
    private String taskName;
    private final Project project;
    private Integer timeRequired;
    private BigDecimal reward;
    private Boolean isComplete = false;

    public Task(String taskName, Project project, Integer timeRequired, BigDecimal reward) {
        this.taskName = taskName;
        this.project = project;
        this.timeRequired = timeRequired;
        this.reward = reward;
    }
    public Task getTask(String taskName) {
        if (Objects.equals(taskName, this.taskName)) {
            return this;
        } else {
            return null;
        }
    }
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Project getProject() {
        return project;
    }

    public Integer getTimeRequired() {
        return timeRequired;
    }

    public BigDecimal getReward() {
        return reward;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }

    public void setTimeRequired(Integer timeRequired) {
        this.timeRequired = timeRequired;
    }

    public void setReward(BigDecimal reward) {
        this.reward = reward;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(taskName, task.taskName) && Objects.equals(project, task.project) && Objects.equals(timeRequired, task.timeRequired) && Objects.equals(reward, task.reward) && Objects.equals(isComplete, task.isComplete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskName, project, timeRequired, reward, isComplete);
    }
}