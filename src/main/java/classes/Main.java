package classes;

import classes.itcompany.ITCompany;
import classes.ui.MenuSwitch;
public class Main {
    public static void main(String[] args) {
        MenuSwitch menu = new MenuSwitch(new ITCompany("ExampleLLC"));
        menu.mainMenuSwitch();
    }
}
