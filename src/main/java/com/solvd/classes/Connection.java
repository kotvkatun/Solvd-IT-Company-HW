package com.solvd.classes;

public class Connection {
    private static int connectionIDCounter = 0;
    public int connectionID;
    public Connection() {
        connectionIDCounter++;
        this.connectionID = connectionIDCounter;
    }
}
