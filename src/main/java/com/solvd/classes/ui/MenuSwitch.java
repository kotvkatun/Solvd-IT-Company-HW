package com.solvd.classes.ui;

import com.solvd.classes.itcompany.ITCompany;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class MenuSwitch {
    private static final Logger LOGGER;
    private final String menu;


    static {
        LOGGER = LogManager.getLogger(MenuSwitch.class);
    }

    private final ITCompany ITCOMPANY = new ITCompany("ExampleLLC");

    {
        MainMenuUtil.open(ITCOMPANY);
        menu = MenuOptions.getOptions();
    }

    public void mainMenuSwitch() {
        while (true) {
            LOGGER.info("Months passed: " + MainMenuUtil.monthsPassed);
            LOGGER.info(menu);
            switch (Input.menuOptionConsoleInput()) {
                case PROJECT:
                    MainMenuUtil.projectInfo(ITCOMPANY.getProject());
                    break;
                case DEVELOPER:
                    MainMenuUtil.developerInfo(ITCOMPANY.getDeveloperList());
                    break;
                case ASSIGN:
                    MainMenuUtil.assign(ITCOMPANY.getDeveloperList(), ITCOMPANY.getProject());
                    break;
                case NEXT:
                    MainMenuUtil.nextMonth(ITCOMPANY);
                    break;
                case MANAGE:
                    MainMenuUtil.manage(ITCOMPANY.getProject());
                    break;
                case CLEAR:
                    MainMenuUtil.clearProject(ITCOMPANY.getProject());
                    break;
                case TODO:
                    MainMenuUtil.toDoList(ITCOMPANY.getProject().getToDoList());
                    break;
                case FILTER:
                    MainMenuUtil.filterTaskListByReward(ITCOMPANY.getProject().getTaskList());
                    break;
                case OPEN:
                    MainMenuUtil.open(ITCOMPANY);
                    break;
                case SAVE:
                    MainMenuUtil.save(ITCOMPANY.getProject());
                    break;
                case EXIT:
                    System.exit(0);
            }
        }
    }
}
