package Controlador;

import Modelo.Pieza;

import java.awt.*;
import java.util.ArrayList;

public interface ControlInterface {

    boolean colocarPiezas(int dimension, Pieza[] piezas);
    Image[][] getTablero();
}
