package classes.developer;

import classes.interfaces.Addable;
import classes.interfaces.CreatableFromInput;
import classes.itcompany.ITCompany;
import classes.project.Task;
import classes.ui.Input;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public class Developer extends AbstractDeveloper implements CreatableFromInput, Addable {
    private static final Logger LOGGER = LogManager.getLogger(Developer.class);
    private final Float GRADE_MODIFIER;
    protected BigDecimal salary = new BigDecimal("0.0");
    public Developer(Grade grade, String developerName) {
        super(grade, developerName);
        this.GRADE_MODIFIER = switch (grade) {
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
        if (task.getTimeRequired() * GRADE_MODIFIER < this.timeAmount) {
            salary = salary.add(task.getReward().multiply(multiplier));
        } else {
            salary = salary.add(task.getReward());
        }
        task.setComplete(true);
    }
    @Override
    public String toString() {
        return  grade + " developer" +
                "\nName: " + developerName +
                "\nSalary: " + salary.toString();
    }

    public Float getGRADE_MODIFIER() {
        return GRADE_MODIFIER;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    @Override
    public Developer createFromInput() {
        LOGGER.info("Enter developer grade");
        Grade grade = null;
        while(grade == null) {
            switch(Input.stringConsoleInput().toLowerCase()) {
                case "junior":
                    grade = Grade.JUNIOR;
                    break;
                case "middle":
                    grade = Grade.MIDDLE;
                    break;
                case "senior":
                    grade = Grade.SENIOR;
                    break;
                default:
                    LOGGER.info("Incorrect grade. Try 'junior', 'middle' or 'senior'");
            }
        }
        LOGGER.info("Enter developer's name");
        return new Developer(grade, Input.stringConsoleInput());
    }

    @Override
    public void addToBaseList(ITCompany itCompany) {
        itCompany.getDeveloperList().add(this);
    }
}