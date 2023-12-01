package com.solvd.classes.itcompany;

import com.solvd.classes.developer.AbstractDeveloper;
import com.solvd.classes.developer.Developer;
import com.solvd.classes.developer.Grades;
import com.solvd.classes.project.Project;
import com.solvd.classes.project.Task;

import java.util.ArrayList;
import java.util.List;

public final class ITCompany implements Clearable, Addable {
    private String itCompanyName;
    private Project project;
    private List<Developer> developerList;

    {
        Developer junVasya = new Developer(Grades.JUNIOR, "Vasya Pupkin");
        Developer midPetya = new Developer(Grades.MIDDLE, "Petya Petrov");
        Developer senPomidor = new Developer(Grades.SENIOR, "Senior Pomidor");
        List<Developer> developerList = new ArrayList<>();
        developerList.add(junVasya);
        developerList.add(midPetya);
        developerList.add(senPomidor);
        this.developerList = developerList;
    }

    public ITCompany(String itCompanyName) {
        this.itCompanyName = itCompanyName;
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

    public void refreshDeveloperTime() {
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
