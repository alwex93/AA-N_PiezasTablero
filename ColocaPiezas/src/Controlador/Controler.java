package Controlador;

import Modelo.ModelInterface;
import Modelo.Pieza;
import Modelo.Piezas.Dama;
import Modelo.Posicion;

import java.util.ArrayList;

public class Controler {

    private ArrayList<Pieza> piezas;
    private ModelInterface modelo;

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

    private void colocarPiezasRecursivo(int nPieza){
        Posicion[] posicionesLibres = modelo.getPosLibres();
        boolean solucion = false;
        for (int pos = 0; pos < posicionesLibres.length && piezas.size() > 0; pos++){
            Posicion posicion = posicionesLibres[pos];
            Pieza pieza = piezas.remove(nPieza);
            if (modelo.colocarPieza(posicion, pieza)){
                if (piezas.size() > 0){
                    colocarPiezasRecursivo(++nPieza);
                    if (!solucion){
                        modelo.quitarPieza(posicion, pieza);
                    }
                }
            }
        }
        if (!true){
            solucion = true;
        } else{
            solucion = false;
        }
        /*
          Inicialitzar el conjunt d’operadors possibles per a l’estat actual;
          encertat:=false; {*s’actualitza a true un cop s’ha obtingut una solucio completa a aquest nivell de recursió*}
          while (solucio_incompleta i existeixen operadors per aplicar) do begin
            seleccionar un operador no aplicat per aplicar a estat_actual;
            if estat_resultant és acceptable then begin
                anotar-lo;
                if solució_incompleta then begin
                    assajar(estat_resultant);
                    if not(encertat) then eliminar anotacio
                end if
            end if
          end while
          if not(solucio_incompleta) then encertat:=true
          else encertat:=false
         */
    }
}
