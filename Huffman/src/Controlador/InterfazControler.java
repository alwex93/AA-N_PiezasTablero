package Controlador;

import java.io.File;

public interface InterfazControler {

    void comprimirFichero(File fichero);
    String getFileLenghtAntes();
    String getFileLenghtDespues();
    boolean generarFichero();
}
