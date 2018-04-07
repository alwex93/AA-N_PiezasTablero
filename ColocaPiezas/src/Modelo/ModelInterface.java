package Modelo;

import java.util.ArrayList;

public interface ModelInterface {

    ArrayList<Posicion> getPosLibres();
    void quitarPieza(Posicion pos, Pieza pieza);
    boolean colocarPieza(Posicion pos, Pieza pieza);
    boolean piezaColocada(Pieza pieza);
    void print();
}
