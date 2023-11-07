package classes.itcompany;

import classes.developer.Contractor;
import classes.developer.Developer;
import classes.initialiser.DeveloperInitialiser;
import classes.interfaces.Clearable;
import classes.project.Project;

import java.util.Iterator;
import java.util.List;

public final class ITCompany implements Clearable {
    private String itCompanyName;
    private Project project;
    private List<Developer> developerList;
    private List<Contractor> contractorList;

    public ITCompany (String itCompanyName) {
        this.itCompanyName = itCompanyName;
    }
    public static ITCompany initialiseITCompany() {
        ITCompany itCompany = new ITCompany("ExampleLLC");
        itCompany.setDeveloperList(DeveloperInitialiser.initialiseDeveloperList());
        itCompany.setProject(null);
        return itCompany;
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

    public List<Contractor> getContractorList() {
        return contractorList;
    }

    public void setContractorList(List<Contractor> contractorList) {
        this.contractorList = contractorList;
    }

    @Override
    public void clear() {
        this.getDeveloperList().clear();
        this.getContractorList().clear();
        this.setProject(null);
    }
}
