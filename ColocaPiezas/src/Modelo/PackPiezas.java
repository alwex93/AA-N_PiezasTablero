package Modelo;

import Modelo.Pieza;
import Modelo.Piezas.*;

import java.util.ArrayList;

public class PackPiezas {

    private int damas;
    private int torres;
    private int alfil;
    private int inv1;
    private int inv2;

    public PackPiezas(){
        damas = 1;
        torres = alfil = inv1 = inv2 = 0;
    }

    public PackPiezas(int damas, int torres, int alfil, int inv1, int inv2){
        this.damas = damas;
        this.torres = torres;
        this.alfil = alfil;
        this.inv1 = inv1;
        this.inv2 = inv2;
    }

    public Pieza[] getPiezas(){
        Pieza[] piezas = new Pieza[damas + torres + alfil + inv1 + inv2];
        int pArray = 0;
        int n;
        for (n = damas; n > 0; n--){
            piezas[pArray++] = new Dama();
        }
        for (n = torres; n > 0; n--){
            piezas[pArray++] = new Torre();
        }
        for (n = alfil; n > 0; n--){
            piezas[pArray++] = new Alfil();
        }
        for (n = inv1; n > 0; n--){
            piezas[pArray++] = new Inv1();
        }
        for (n = inv2; n > 0; n--){
            piezas[pArray++] = new Inv2();
        }
        return piezas;
    }
}
