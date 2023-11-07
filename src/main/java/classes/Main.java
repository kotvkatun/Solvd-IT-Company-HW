package classes;

import classes.itcompany.ITCompany;
import classes.ui.Input;
import classes.ui.MainMenu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER;
    static {
        LOGGER = LogManager.getLogger(Main.class);
    }
    public static void main(String[] args) {
        ITCompany itCompany = ITCompany.initialiseITCompany();
        while (true) {
            MainMenu.showMenu();
            switch (Input.stringConsoleInput()) {
                case "project":
                    MainMenu.projectInfo(itCompany.getProject());
                    break;
                case "developer":
                    MainMenu.developerInfo(itCompany.getDeveloperList());
                    break;
                case "assign":
                    MainMenu.assign(itCompany.getDeveloperList(), itCompany.getProject().getTaskList());
                    break;
                case "manage":
                    MainMenu.manage(itCompany.getProject());
                    break;
                case "open":
                    MainMenu.open(itCompany);
                    break;
                case "save":
                    MainMenu.save(itCompany.getProject());
                    break;
                case "exit":
                    System.exit(0);
                default:
                    LOGGER.info("Wrong command.");
                    break;
            }
        }
    }
}
