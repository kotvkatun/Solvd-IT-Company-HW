package com.solvd.classes.itcompany;

import com.solvd.classes.developer.AbstractDeveloper;
import com.solvd.classes.developer.Developer;
import com.solvd.classes.initializer.DeveloperInitializer;
import com.solvd.classes.interfaces.Addable;
import com.solvd.classes.interfaces.Clearable;
import com.solvd.classes.project.Project;
import com.solvd.classes.project.Task;

import java.util.List;

public final class ITCompany implements Clearable, Addable {
    private String itCompanyName;
    private Project project;
    private List<Developer> developerList = ITCompany.initializeDeveloperList();

    public ITCompany(String itCompanyName) {
        this.itCompanyName = itCompanyName;
    }

    private static List<Developer> initializeDeveloperList() {
        return DeveloperInitializer.initializeDeveloperList();
    }

    public String getItCompanyName() {
        return itCompanyName;
    }

    public void setItCompanyName(String itCompanyName) {
        this.itCompanyName = itCompanyName;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Developer> getDeveloperList() {
        return developerList;
    }

    public void setDeveloperList(List<Developer> developerList) {
        this.developerList = developerList;
    }

    public void switchToNextMonth() {
        developerList.forEach(AbstractDeveloper::refreshTime);
    }

    @Override
    public void clear() {
        this.getDeveloperList().clear();
        this.setProject(null);
    }

    @Override
    public void addToBaseList(Developer developer) {
        this.developerList.add(developer);
    }

    @Override
    public void addToBaseList(Task task) {
        this.getProject().getTaskList().add(task);
    }
}
