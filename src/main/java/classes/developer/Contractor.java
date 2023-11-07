package classes.developer;

import classes.interfaces.Addable;
import classes.itcompany.ITCompany;

public class Contractor extends Developer implements Addable {
    public Contractor(Grade grade, String developerName) {
        super(grade, developerName);
    }

    @Override
    public String toString() {
        return  grade + " contractor" +
                "\nName: " + developerName +
                "\nSalary: " + salary.toString();
    }
    @Override
    public void addToBaseList(ITCompany itCompany) {
        itCompany.getContractorList().add(this);
    }
}
