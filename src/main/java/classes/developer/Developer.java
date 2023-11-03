package classes.developer;

import classes.project.Task;

import java.math.BigDecimal;

public class Developer extends AbstractDeveloper{
    private Float gradeModifier;
    private BigDecimal salary = new BigDecimal("0.0");
    public Developer(Grade grade, String developerName) {
        super(grade, developerName);
        this.gradeModifier = switch (grade) {
            case JUNIOR -> 1.5f;
            case MIDDLE -> 1.0f;
            case SENIOR -> 0.5f;
        };
    }

    @Override
    public void completeTask(Task task) {
        if (task.getComplete()) {
            System.out.println("This task has already been completed!");
            return;
        }

        BigDecimal multiplier = new BigDecimal("2");
        if (task.getTimeRequired() * gradeModifier < this.timeAmount) {
            salary = salary.add(task.getReward().multiply(multiplier));
        } else {
            salary = salary.add(task.getReward());
        }
        task.setComplete(true);
    }

    @Override
    public String toString() {
        return "Developer" +
                "\nGrade: " + grade +
                "\nName: " + developerName +
                "\nSalary: " + salary.toString();
    }

    public Float getGradeModifier() {
        return gradeModifier;
    }

    public BigDecimal getSalary() {
        return salary;
    }

}