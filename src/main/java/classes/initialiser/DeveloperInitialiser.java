package classes.initialiser;

import classes.developer.Developer;
import classes.developer.Grade;

import java.util.ArrayList;
import java.util.List;

public class DeveloperInitialiser {
    public static List<Developer> initialiseDeveloperList() {
        Developer junVasya = new Developer(Grade.JUNIOR, "Vasya Pupkin");
        Developer midPetya = new Developer(Grade.MIDDLE, "Petya Petrov");
        Developer senPomidor = new Developer(Grade.SENIOR, "Senior Pomidor");
        List<Developer> developerList = new ArrayList<>();
        developerList.add(junVasya);
        developerList.add(midPetya);
        developerList.add(senPomidor);
        return developerList;
    }
}
