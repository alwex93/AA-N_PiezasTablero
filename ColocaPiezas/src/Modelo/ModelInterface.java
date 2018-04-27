package Modelo;

import java.awt.*;
import java.util.ArrayList;

public interface ModelInterface {

    ArrayList<Posicion> getPosLibres();
    void quitarPieza(Posicion pos, Pieza pieza);
    boolean colocarPieza(Posicion pos, Pieza pieza);
    boolean piezaColocada(Pieza pieza);
    void print();
    PackPiezas getPack(int damas, int torres, int alfil, int inv1, int inv2);
    int getLenght();
    Image getSquare(Posicion pos);
}
