package Modelo;

import java.util.ArrayList;

public class TablaFrecuencias {
    private byte[] caracteresExcluidos = System.getProperty("line.separator").getBytes();

    private ArrayList<Simbolo> tabla;

    public TablaFrecuencias() {
        tabla = new ArrayList<>();
    }

    public void addSimbolo(Simbolo simbolo){
        for (byte b : caracteresExcluidos) {
            if (simbolo.getValor_ascii() == b){
                return;
            }
        }
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

    public void changeElement(int posLower, int posGreater) {
        Simbolo aux = tabla.get(posLower);
        tabla.set(posLower, tabla.get(posGreater));
        tabla.set(posGreater, aux);
    }

    public int getFrecuencia(int pos) {
        return tabla.get(pos).getFrequencia();
    }

    public Simbolo getSimbolo(int pos){
        return tabla.get(pos);
    }

    public int getSizeTable() {
        return tabla.size();
    }
}
