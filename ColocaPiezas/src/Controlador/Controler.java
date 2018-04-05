package Controlador;

import Modelo.ModelInterface;
import Modelo.Pieza;
import Modelo.Piezas.Dama;
import Modelo.Posicion;

import java.util.ArrayList;

public class Controler implements ControlInterface{

    private ArrayList<Pieza> piezas;
    private ModelInterface modelo;
    private boolean solucion = false, colocada = false;

    public Controler(){
        piezas = new ArrayList<>();
    }

    public void cargarPiezas(int damas, int torres, int alfil, int int1, int inv2){
        int q;
        for (q = damas; q >= 0; q--){
            piezas.add(new Dama());
        }
        for (q = torres; q >= 0; q--){
            piezas.add(new Dama());
        }
        for (q = alfil; q >= 0; q--){
            piezas.add(new Dama());
        }
        for (q = int1; q >= 0; q--){
            piezas.add(new Dama());
        }
        for (q = inv2; q >= 0; q--){
            piezas.add(new Dama());
        }
    }

    public boolean colocarPiezas(){
        if (piezas.size() != 0){
            colocarPiezasRecursivo(0);
        }
        return false;
    }

    private boolean colocarPiezasRecursivo(int nPieza){
        Posicion[] posLibres = modelo.getPosLibres();
        int flagP = 0;
        solucion = false;
        while (nPieza < piezas.size() && flagP < posLibres.length && !solucion) {
            Pieza pieza = piezas.remove(nPieza);
            Posicion coordenadas = new Posicion(0,0);
            while (!colocada && flagP < posLibres.length) {
                coordenadas = posLibres[flagP];
                colocada = modelo.colocarPieza(coordenadas, pieza);
                flagP++;
            }
            colocada = false;
            //Print(tablero);
            //Thread.sleep(1000);
            //this.repaint();
            if (nPieza < piezas.size() && modelo.piezaColocada(pieza)) {
                solucion = colocarPiezasRecursivo(nPieza + 1);
                if (!solucion) {
                    modelo.quitarPieza(coordenadas, pieza);

                    //Print(tablero);
                    //Thread.sleep(1000);
                    //this.repaint();
                    if (flagP < posLibres.length) {
                        flagP++;
                    }

                }
            }
        }
        if (nPieza < piezas.size()) {
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
