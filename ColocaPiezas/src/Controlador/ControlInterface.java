package Controlador;

import Modelo.Pieza;

import java.awt.*;
import java.util.ArrayList;

public interface ControlInterface {

    //Interacciones usuario
    void abrirVentanaDatos();
    boolean colocarPiezas(int dimension, Pieza[] piezas);

    //Interaciones vista

    //Interacciones modelo
    Image[][] getTablero();
}
