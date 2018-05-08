package Modelo;

public interface InterfazModelo {
    //Vista
    String[] getColumnNames();
    Object[][] getTablaFrecuencias();

    //Controlador
    int getFrecuencia(int pos);
    byte getValue(int pos);
    int sizeTable();
    void addSimbolo(int simbolo);
    void showFrecuencias();
    void changeElement(int posLower, int posGreater);


}
