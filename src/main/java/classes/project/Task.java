package classes.project;

import classes.interfaces.CreatableFromInput;
import classes.ui.Input;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.Objects;

public class Task implements CreatableFromInput {
    private static final Logger LOGGER = LogManager.getLogger(Task.class);
    private String taskName;
    private final Project PROJECT;
    private Integer timeRequired;
    private BigDecimal reward;
    private Boolean isComplete = false;

    public Task(String taskName, Project PROJECT, Integer timeRequired, BigDecimal reward) {
        this.taskName = taskName;
        this.PROJECT = PROJECT;
        this.timeRequired = timeRequired;
        this.reward = reward;
    }

    public Task(String taskName, Integer timeRequired, BigDecimal reward) {
        this.taskName = taskName;
        this.PROJECT = null;
        this.timeRequired = timeRequired;
        this.reward = reward;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Project getPROJECT() {
        return PROJECT;
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
        return Objects.equals(taskName, task.taskName) && Objects.equals(PROJECT, task.PROJECT) && Objects.equals(timeRequired, task.timeRequired) && Objects.equals(reward, task.reward) && Objects.equals(isComplete, task.isComplete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskName, PROJECT, timeRequired, reward, isComplete);
    }

    @Override
    public Task createFromInput() {
        LOGGER.info("Enter new task name:");
        String taskName = Input.stringConsoleInput();
        Integer timeRequired = null;
        while (timeRequired == null) {
            try {
                LOGGER.info("Enter time required:");
                timeRequired = Integer.parseInt(Input.stringConsoleInput());
            } catch (NumberFormatException e) {
                LOGGER.info("Not an integer. Try again?");
            }
        }
        LOGGER.info("Enter reward:");
        BigDecimal reward = null;
        while (reward == null) {
            try {
                reward = new BigDecimal(Input.stringConsoleInput());
            } catch (NumberFormatException e) {
                LOGGER.info("Incorrect format. Try separating mantissa with a dot?");
            }
        }
        return new Task(taskName, timeRequired, reward);
    }

    public Task createFromInput(boolean isComplete) {
        Task task = this.createFromInput();
        task.setComplete(isComplete);
        return task;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskName='" + taskName + '\'' +
                ", timeRequired=" + timeRequired +
                ", reward=" + reward +
                ", isComplete=" + isComplete +
                '}';
    }
}