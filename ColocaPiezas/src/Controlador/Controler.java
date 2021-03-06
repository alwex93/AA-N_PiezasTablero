package Controlador;

import Modelo.ModelInterface;
import Modelo.Pieza;
import Modelo.Posicion;
import Modelo.Tablero;
import Vista.Seleccionador;

import java.awt.*;
import java.util.ArrayList;

public class Controler implements ControlInterface{

    private Pieza[] piezas;
    private ModelInterface modelo;
    private boolean solucion = false, colocada = false;

    public Controler(int dim){
        modelo = new Tablero(dim);
    }

    public boolean colocarPiezas(int dimension, Pieza[] piezas){
        modelo.actualizarDimensionTablero(dimension);
        if (piezas.length > 0){
            this.piezas = piezas;
            return colocarPiezasRecursivo(0);
        }
        return false;
    }

    @Override
    public Image[][] getTablero() {
        return modelo.getTablero();
    }

    private boolean colocarPiezasRecursivo(int nPieza){
        ArrayList<Posicion> posLibres = modelo.getPosLibres();
        int flagP = 0;
        solucion = false;
        while (nPieza < piezas.length && flagP < posLibres.size() && !solucion) {
            Pieza pieza = piezas[nPieza];
            Posicion coordenadas;
            do {
                coordenadas = posLibres.get(flagP);
                colocada = modelo.colocarPieza(coordenadas, pieza);
                flagP++;
            }while (!colocada && flagP < posLibres.size());
            colocada = false;
            if (nPieza < piezas.length && modelo.piezaColocada(pieza)) {
                solucion = colocarPiezasRecursivo(nPieza + 1);
                if (!solucion) {
                    modelo.quitarPieza(coordenadas, pieza);
                    if (flagP < posLibres.size()) {
                        flagP++;
                    }

                }
            }
        }
        if (nPieza < piezas.length) {
            boolean ret = true;
            for (Pieza pieza : piezas) {
                ret = ret && modelo.piezaColocada(pieza);
            }
            return ret;
        } else {
            return true;
        }
    }
}
