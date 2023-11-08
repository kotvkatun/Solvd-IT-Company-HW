package classes.developer;

import classes.project.Task;

import java.util.Objects;

public abstract class AbstractDeveloper {
    protected Grade grade;
    protected String developerName;
    protected final Integer timeAmount = 180;
    public AbstractDeveloper(Grade grade, String developerName) {
        this.grade = grade;
        this.developerName = developerName;
    }
    public abstract void completeTask(Task task);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractDeveloper that = (AbstractDeveloper) o;
        return grade == that.grade && Objects.equals(developerName, that.developerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grade, developerName);
    }
}