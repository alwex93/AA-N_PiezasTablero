package Modelo;

import javax.swing.*;

public interface ModelInterface {

    //funciones del controlador
    Bandera cargarBanderaAdivinar(int bandera, int numPixeles);

    //funciones de la vista
    void CambiarCarpeta(String rutaCarpeta);
    void setImagenBlanca(String path);
    String[] getNameBanderas();
    String getNameBandera(int bandera);
    Icon getBanderaImg(int bandera);
    Icon getImagenBlanca();
}
