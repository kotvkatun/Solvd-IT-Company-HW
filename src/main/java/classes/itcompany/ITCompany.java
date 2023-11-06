package classes.itcompany;

import classes.developer.Developer;
import classes.project.Project;

import java.util.List;

public class ITCompany {
    private String itCompanyName;
    private Project project;
    private List<Developer> developerList;

    public ITCompany (String itCompanyName) {
        this.itCompanyName = itCompanyName;
    }
}
