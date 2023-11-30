package com.solvd.classes.ui;

public enum MenuOption {
    PROJECT("📒 project - Show project info\n"),
    DEVELOPER("👨🏻‍💻 developer - Show developer info\n"),
    ASSIGN("👉🏻 assign - Assign tasks to developers\n"),
    MANAGE("🛠 manage - Add or remove tasks from a project\n"),
    CLEAR("🧹 clear - Remove all current tasks from selected project\n"),
    UNDUPE("🗂 undupe - Remove all duplicate tasks from selected projects\n"),
    TODO("📜 todo - Get a list of tasks to do (forces undupe).\n"),
    NEXT("🗓 next - Set time to next month and refresh developers time limits.\n"),
    OPEN("💽 open - Load an existing project from a file\n"),
    SAVE("💾 save - Save current project to a file (will keep project name)\n"),
    EXIT("exit - Exit application");

    private final String DESCRIPTION;

    MenuOption(String description) {
        this.DESCRIPTION = description;
    }

    public static String getOptions() {
        StringBuilder optionsBuilder = new StringBuilder();
        for (MenuOption option : MenuOption.values()) {
            optionsBuilder.append(option.DESCRIPTION);
        }
        return optionsBuilder.toString();
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }
}
