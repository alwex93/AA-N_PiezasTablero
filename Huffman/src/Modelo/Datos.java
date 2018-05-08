package Modelo;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Created by Andrés Ramos Seguí.
 */
public class Datos {
    private final PriorityQueue<Nodo> heap_nodos;
    private final ArrayList<Simbolo> tabla_simbolos_frecuencias;
    private final byte[] caracteresExcluidos = System.getProperty("line.separator").getBytes();

    private final int CHAR_POS = 0, FRECUENCY_POS = 1, HAFFMANCODE_POS = 2;
    private final String[] COLUMN_NAMES = {"Caracter", "Frecuencia", "Codigo Huffman"};


    public Datos() {
        tabla_simbolos_frecuencias = new ArrayList<>();
        heap_nodos = new PriorityQueue<>();

    }

    public void addSimbolo(int simbolo){
        for (byte b : caracteresExcluidos) {
            if (simbolo == b){
                return;
            }
        }
        int pos = tabla_simbolos_frecuencias.indexOf(new Simbolo(simbolo));
        if (pos != -1){
            tabla_simbolos_frecuencias.get(pos).addUnoMas();

        } else {
            tabla_simbolos_frecuencias.add(new Simbolo(simbolo));
        }
    }

    public void showFrecuencias(){
        for (Simbolo b : tabla_simbolos_frecuencias) {
            System.out.println(b.toString());
        }
    }

    public Object[][] getTablaFrecuencias() {
        Object[][] data = new Object[tabla_simbolos_frecuencias.size()][COLUMN_NAMES.length];
        int pos = 0;
        for (Simbolo simbolo : tabla_simbolos_frecuencias) {
            data[pos][CHAR_POS] = (char) simbolo.getValor_ascii();
            data[pos][FRECUENCY_POS] = simbolo.getFrequencia();
            data[pos][HAFFMANCODE_POS] = 0;
            pos++;
        }
        return data;
    }

    public String[] getColumnNames(){
        return COLUMN_NAMES;
    }
}
