package Modelo;

import javax.swing.*;

public interface ModelInterface {

    //funciones del controlador
    String getBanderaPath(int bandera);

    //funciones de la vista
    String[] getNameBanderas();
    Icon getBanderaImg(int bandera);
    Icon getImagenBlanca();
}
