package Controlador;

import java.io.File;

public interface InterfazControler {

    double comprimirFichero(File fichero);
    void generarArbolHuffman();
    void setHaffmanValues();
    String getFileLenght();
}
