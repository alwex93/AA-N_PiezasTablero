package Modelo;

import Controlador.HeapNodos.HeapNodos;

public class Datos implements InterfazModelo{
    private TablaFrecuencias tablaSimbolos;
    private HeapNodos heapNodos;
    long fileLenght;

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
            data[pos][HAFFMANCODE_POS] = simbolo.getHaffmanValue();
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
    public void setHaffmanValue(byte simbol, String value) {
        tablaSimbolos.setValue(simbol, value);
    }

    @Override
    public String getHaffmanValue(byte simbolo) {
        return tablaSimbolos.getHuffmanValue(new Simbolo(simbolo));
    }

    @Override
    public String[] getColumnNames(){
        return COLUMN_NAMES;
    }

    @Override
    public void setFileLenght(long lenght){
        fileLenght = lenght;
    }

    @Override
    public double getFileLenght() {
        return fileLenght;
    }
}
