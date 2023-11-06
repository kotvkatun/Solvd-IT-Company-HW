package classes.project;

import classes.ui.Input;

import java.math.BigDecimal;
import java.util.*;

public class Project {
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
        for(Task task: this.taskList) {
            String marker = (task.getComplete())? "☑️\n" : "❌\n";
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
    public void removeTask(Task task) {
        this.taskList.remove(task);
    }
    public void addTaskFromInput() {
        System.out.println("Enter new task name:");
        String taskName = Input.stringConsoleInput();
        Integer timeRequired = null;
        while (timeRequired == null) {
            try{
                System.out.println("Enter time required:");
                timeRequired = Integer.parseInt(Input.stringConsoleInput());
            } catch (NumberFormatException e) {
                System.out.println("Not an integer. Try again?");
            }
        }
        System.out.println("Enter reward:");
        BigDecimal reward = null;
        while (reward == null) {
            try {
                reward = new BigDecimal(Input.stringConsoleInput());
            } catch (NumberFormatException e) {
                System.out.println("Incorrect format. Try separating mantissa with a dot?");
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
}