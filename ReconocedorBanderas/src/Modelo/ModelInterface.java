package Modelo;

import javax.swing.*;

public interface ModelInterface {

    //funciones del controlador
    Icon cargarBanderaAdivinar(int bandera, int numPixeles);

    //funciones de la vista
    void CambiarCarpeta(String nombreCarpeta);
    void setImagenBlanca(String path);
    String[] getNameBanderas();
    Icon getBanderaImg(int bandera);
    Icon getImagenBlanca();
}
