package classes;

import classes.project.Task;
import classes.project.TasksLinkedList;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        //MenuSwitch menu = new MenuSwitch();
        //menu.mainMenuSwitch();
        TasksLinkedList<Task> taskTasksLinkedList = new TasksLinkedList<>();
        taskTasksLinkedList.add(new Task("tasku1", 12, new BigDecimal("5000.0")));
        taskTasksLinkedList.add(new Task("tasku2", 12, new BigDecimal("5000.0")));
        taskTasksLinkedList.add(new Task("tasku3", 12, new BigDecimal("5000.0")));
        taskTasksLinkedList.printList();
        System.out.println(taskTasksLinkedList.size());
        System.out.println(taskTasksLinkedList.get(0));
    }
}
