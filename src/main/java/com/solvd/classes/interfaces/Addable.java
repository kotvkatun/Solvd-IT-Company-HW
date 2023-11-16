package com.solvd.classes.interfaces;

import com.solvd.classes.developer.Developer;
import com.solvd.classes.project.Task;

public interface Addable {
    void addToBaseList(Developer developer);

    void addToBaseList(Task task);
}
