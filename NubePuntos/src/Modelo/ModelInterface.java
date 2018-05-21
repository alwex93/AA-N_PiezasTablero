package Modelo;

public interface ModelInterface {

    int getLenght();
    void setCercanos(int cercano1, int cercano2);
    boolean isEquals(int elem1, int elem2);
    long distancia(int p1, int p2);
    int getCoordenadaX(int punto);
    void exchangeNumbers(int i, int j);
    void printInfoPuntos();
    ModelInterface copiaModelo(ModelInterface data);
    void generarPuntos(int n, int maxX, int maxY);
}
