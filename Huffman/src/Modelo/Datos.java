package Modelo;

import Controlador.HeapNodos.HeapNodos;

public class Datos implements InterfazModelo{
    private TablaFrecuencias tablaSimbolos;
    private HeapNodos heapNodos;

    private final int CHAR_POS = 0, FRECUENCY_POS = 1, HAFFMANCODE_POS = 2;
    private final String[] COLUMN_NAMES = {"Value(Caracter)", "Frecuencia", "Codigo Huffman"};


    public Datos() {
        tablaSimbolos = new TablaFrecuencias();
    }

    @Override
    public Object[][] getTablaFrecuencias() {
        Object[][] data = new Object[tablaSimbolos.getSizeTable()][COLUMN_NAMES.length];
        Simbolo simbolo;
        for (int pos = 0; pos < tablaSimbolos.getSizeTable(); pos++){
            simbolo = tablaSimbolos.getSimbolo(pos);
            data[pos][CHAR_POS] = simbolo.getValor_ascii() + "(" + (char) simbolo.getValor_ascii() + ")";
            data[pos][FRECUENCY_POS] = simbolo.getFrequencia();
            data[pos][HAFFMANCODE_POS] = 0;
        }
        return data;
    }

    @Override
    public int getFrecuencia(int pos) {
        return tablaSimbolos.getFrecuencia(pos);
    }

    @Override
    public byte getValue(int pos) {
        return tablaSimbolos.getSimbolo(pos).getValor_ascii();
    }

    @Override
    public int sizeTable() {
        return tablaSimbolos.getSizeTable();
    }

    @Override
    public void addSimbolo(int simbolo) {
        tablaSimbolos.addSimbolo(new Simbolo(simbolo));
    }

    @Override
    public void showFrecuencias() {
        tablaSimbolos.showFrecuencias();
    }

    @Override
    public void changeElement(int posLower, int posGreater) {
        tablaSimbolos.changeElement(posLower, posGreater);
    }

    @Override
    public String[] getColumnNames(){
        return COLUMN_NAMES;
    }
}
