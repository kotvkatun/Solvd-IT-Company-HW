package com.solvd.classes.developer;

import com.solvd.classes.itcompany.CreatableFromInput;
import com.solvd.classes.project.Task;
import com.solvd.classes.ui.Input;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public class Developer extends AbstractDeveloper implements CreatableFromInput {
    private static final Logger LOGGER = LogManager.getLogger(Developer.class);
    private static final BigDecimal DOUBLE = new BigDecimal(2);
    protected BigDecimal salary = new BigDecimal("0.0");

    public Developer(Grades grade, String developerName) {
        super(grade, developerName);
    }

    @Override
    public void completeTask(Task task) {
        if (task.getComplete()) {
            System.out.println("This task has already been completed!");
            return;
        }
        if (task.getTimeRequired() * getGRADE_MODIFIER() > this.timeAmount) {
            this.timeAmount = 0;
            salary = salary.add(task.getReward().multiply(DOUBLE));
        } else {
            this.timeAmount -= task.getTimeRequired();
            salary = salary.add(task.getReward());
        }
        task.setComplete(true);
    }

    @Override
    public String toString() {
        return grade + " developer" +
                "\nName: " + developerName +
                "\nSalary: " + salary.toString() +
                "\nTime left this month: " + timeAmount;
    }

    public Float getGRADE_MODIFIER() {
        return grade.GRADE_MODIFIER;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    @Override
    public Developer createFromInput() {
        LOGGER.info("Enter developer grade");
        IGetGrade<Grades> grader = s -> {
            Grades g = null;
            switch (s) {
                case "junior":
                    g = Grades.JUNIOR;
                    break;
                case "middle":
                    g = Grades.MIDDLE;
                    break;
                case "senior":
                    g = Grades.SENIOR;
                    break;
                default:
                    LOGGER.info("Incorrect grade. Try 'junior', 'middle' or 'senior'");
            }
            return g;
        };
        while (grade == null) {
            grade = grader.getGrade(Input.stringConsoleInput());
        }
        LOGGER.info("Enter developer's name");
        return new Developer(grade, Input.stringConsoleInput());
    }
}