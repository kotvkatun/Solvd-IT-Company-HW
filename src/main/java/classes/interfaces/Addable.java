package classes.interfaces;

import classes.developer.Developer;
import classes.project.Task;

public interface Addable {
    void addToBaseList(Developer developer);

    void addToBaseList(Task task);
}
