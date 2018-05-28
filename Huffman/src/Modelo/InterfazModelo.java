package Modelo;

public interface InterfazModelo {
    //Vista
    String[] getColumnNames();
    Object[][] getTablaFrecuencias();

    //Controlador
    double getFileLenght();
    void setFileLenght(long lenght);
    int getFrecuencia(int pos);
    byte getValue(int pos);
    int sizeTable();
    void addSimbolo(int simbolo);
    //void showFrecuencias();
    void setHaffmanValue(byte simbol, String value);
    String getHaffmanValue(byte simbolo);

}
