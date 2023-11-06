package classes.json;

import classes.project.Project;

import java.io.IOException;

public interface JSONExternalizable {
    boolean writeJSON () throws IOException;
    Project readJSON (String filename) throws IOException;
}
