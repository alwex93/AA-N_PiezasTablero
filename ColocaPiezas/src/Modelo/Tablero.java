package Modelo;

public class Tablero {

    private int[][] tablero;
    private static int DEFAULT = 9;
    private int dimension;
    private int posLibres;

    private static int ORDER_COLOCAR = -1;
    private static int ORDER_QUITAR = 0;

    public Tablero(){
        tablero = new int[DEFAULT][DEFAULT];
        dimension = DEFAULT;
        posLibres = tablero.length;
    }

    public Tablero(int dim){
        tablero = new int[dim][dim];
        for(int fila = 0; fila < dim; fila++){
            for(int columna = 0; columna < dim; columna++){
                tablero[fila][columna] = 0;
            }
        }
        dimension = dim;
        posLibres = tablero.length;
    }

    public void colocarPieza(Modelo.Posicion pos, Pieza pieza){
        tablero[pos.getX()][pos.getY()] = pieza.getValue();
        colocar(pieza);
    }

    private void colocar(Pieza pieza){
        switch (pieza.getValue()){

        }
    }

    public void quitarPieza(Modelo.Posicion pos, Pieza pieza){
        tablero[pos.getX()][pos.getY()] = 0;
    }

    private void marcarHorizontal(int order, Modelo.Posicion piezaPos){
        for (int columna = 0; columna < dimension; columna++){
            tablero[piezaPos.getX()][columna] = order;
        }
        if (order == ORDER_COLOCAR){
            posLibres -= dimension;
        }
    }

    private void marcarVertical(int order, Modelo.Posicion piezaPos){
        for (int fila = 0; fila < dimension; fila++){
            tablero[fila][piezaPos.getY()] = order;
        }
        if (order == ORDER_COLOCAR){
            posLibres -= dimension;
        }
    }

    private void marcarDiagonalDerecha(int order, Modelo.Posicion piezaPos){
        int posAmenazas = 0;
        for (int fila = piezaPos.getX() - 1; fila >= 0; fila--){
            for (int columna = piezaPos.getY() + 1; columna < dimension; columna++){
                tablero[fila][columna] = order;
                posAmenazas++;
            }
        }
        for (int fila = piezaPos.getX() + 1; fila < dimension; fila++){
            for (int columna = piezaPos.getY() - 1; columna >= 0; columna--){
                tablero[fila][columna] = order;
                posAmenazas++;
            }
        }
        if (order == ORDER_COLOCAR){
            posLibres -= posAmenazas;
        }
    }

    private void marcarDiagonalIzquierda(int order, Modelo.Posicion piezaPos){
        int posAmenazas = 0;
        for (int fila = piezaPos.getX() + 1; fila < dimension; fila++){
            for (int columna = piezaPos.getY() + 1; columna < dimension; columna++){
                tablero[fila][columna] = order;
                posAmenazas++;
            }
        }
        for (int fila = piezaPos.getX() - 1; fila >= 0; fila--){
            for (int columna = piezaPos.getY() - 1; columna >= 0; columna--){
                tablero[fila][columna] = order;
                posAmenazas++;
            }
        }
        if (order == ORDER_COLOCAR){
            posLibres -= posAmenazas;
        }
    }

    public int getPosLibres(){
        return posLibres;
    }
}
