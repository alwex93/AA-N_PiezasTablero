package Controlador;

import java.io.File;

public interface InterfazControler {

    void comprimirFichero(File fichero);
    void generarArbolHuffman();
    void setHaffmanValues();
    String getFileLenght();
}
