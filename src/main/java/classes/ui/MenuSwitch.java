package classes.ui;

import classes.itcompany.ITCompany;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class MenuSwitch {
    private static final Logger LOGGER;
    static {
        LOGGER = LogManager.getLogger(MenuSwitch.class);
    }
    private final ITCompany ITCOMPANY;
    public MenuSwitch (ITCompany itCompany) {
        this.ITCOMPANY = itCompany.initialiseITCompany();
    }
    public void mainMenuSwitch () {
        while (true) {
            MainMenu.showMenu();
            switch (Input.stringConsoleInput()) {
                case "project":
                    MainMenu.projectInfo(ITCOMPANY.getProject());
                    break;
                case "developer":
                    MainMenu.developerInfo(ITCOMPANY.getDeveloperList());
                    break;
                case "assign":
                    MainMenu.assign(ITCOMPANY.getDeveloperList(), ITCOMPANY.getProject());
                    break;
                case "next":
                    MainMenu.nextMonth(ITCOMPANY);
                    break;
                case "manage":
                    MainMenu.manage(ITCOMPANY.getProject());
                    break;
                case "clear":
                    MainMenu.clearProject(ITCOMPANY.getProject());
                    break;
                case "open":
                    MainMenu.open(ITCOMPANY);
                    break;
                case "save":
                    MainMenu.save(ITCOMPANY.getProject());
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
