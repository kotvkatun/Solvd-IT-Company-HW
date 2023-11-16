package com.solvd.classes.initializer;

import com.solvd.classes.developer.Developer;
import com.solvd.classes.developer.Grade;

import java.util.ArrayList;
import java.util.List;

public class DeveloperInitializer {
    public static List<Developer> initializeDeveloperList() {
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
