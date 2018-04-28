package Modelo;

import Vista.PackPiezas;

import java.awt.*;
import java.util.ArrayList;

public interface ModelInterface {

    ArrayList<Posicion> getPosLibres();
    void quitarPieza(Posicion pos, Pieza pieza);
    boolean colocarPieza(Posicion pos, Pieza pieza);
    boolean piezaColocada(Pieza pieza);
    void print();
    void actualizarDimensionTablero(int nuevaDimension);
    Image[][] getTablero();
}
