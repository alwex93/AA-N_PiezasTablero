package Modelo;

import java.util.ArrayList;

public class TablaFrecuencias {

    private ArrayList<Simbolo> tabla;

    public TablaFrecuencias() {
        tabla = new ArrayList<>();
    }

    public void addSimbolo(Simbolo simbolo){
        int pos = tabla.indexOf(simbolo);
        if (pos != -1){
            tabla.get(pos).addUnoMas();

        } else {
            tabla.add(simbolo);
        }
    }

    public void showFrecuencias(){
        for (Simbolo b : tabla) {
            System.out.println(b.toString());
        }
    }

    public int getFrecuencia(int pos) {
        return tabla.get(pos).getFrequencia();
    }

    public Simbolo getSimbolo(int pos){
        return tabla.get(pos);
    }

    public String getHuffmanValue(Simbolo simbolo){
        return tabla.get(tabla.indexOf(simbolo)).getHaffmanValue();
    }

    public void setValue(byte simbolo, String value){
        tabla.get(tabla.indexOf(new Simbolo(simbolo))).setHaffmanValue(value);
    }

    public int getSizeTable() {
        return tabla.size();
    }
}
