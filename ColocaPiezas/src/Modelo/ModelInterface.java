package Modelo;

public interface ModelInterface {

    Modelo.Posicion[] getPosLibres();
    void quitarPieza(Modelo.Posicion pos, Pieza pieza);
    boolean colocarPieza(Modelo.Posicion pos, Pieza pieza);
}
