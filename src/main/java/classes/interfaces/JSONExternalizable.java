package classes.interfaces;


import java.io.IOException;

public interface JSONExternalizable {
    boolean writeJSON () throws IOException;
    Object readJSON (String filename) throws IOException;
}
