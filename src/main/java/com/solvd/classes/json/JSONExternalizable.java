package com.solvd.classes.json;


import java.io.IOException;

public interface JSONExternalizable {
    boolean writeJSON() throws IOException;

    Object readJSON(String filename) throws IOException;
}
