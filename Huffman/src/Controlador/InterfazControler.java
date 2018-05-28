package Controlador;

import java.io.File;

public interface InterfazControler {

    double comprimirFichero(File fichero);
    String getFileLenghtAntes();
    String getFileLenghtDespues();
    boolean generarFichero();
}
