package Controlador;

import Modelo.Datos;

public class ColocaN implements ControlerInterface{

    private Datos modelo;

    public ColocaN(Datos modelo){
        this.modelo = modelo;
    }

    public void distanciaMinima(){
        int masCercano, cercano1 = 0, cercano2 = 0;
        long distanciaMinima = Long.MAX_VALUE;
        ordenarPuntos(0, modelo.getLenght() - 1); //Order by X O(log(n))
        for (int punto = 0; punto < modelo.getLenght() - 1; punto++){
            masCercano = minim(punto, 0, modelo.getLenght() - 1);
            if (distancia(punto, masCercano) < distanciaMinima){
                distanciaMinima = distancia(punto, masCercano);
                cercano1 = punto;
                cercano2 = masCercano;
            }
        }
        modelo.setCercanos(cercano1, cercano2);
    }

    private int minim(int elem, int i, int j){
        int minD, minI;
        if( j - i > 2){
            minI = minim(elem, i, i +(j - i)/2);
            minD = minim(elem, i +(j - i)/2, j);
            return getMinimo(elem, minI, minD);
        } else {
            return getMinimo(elem, i, j);
        }
    }

    private int getMinimo(int elem, int indx1, int indx2){
        if (distancia(elem, indx1) < distancia(elem, indx2)) {
            return indx1;
        } else {
            return indx2;
        }
    }

    private long distancia(int elem, int indx){
        if (modelo.isEquals(elem, indx)) return Long.MAX_VALUE;
        return modelo.distancia(elem, indx);
    }

    private void ordenarPuntos(int lInd, int hInd){
        int i = lInd;
        int j = hInd;
        // calculate pivot number, I am taking pivot as middle index number
        int pivot = modelo.getCoordenadaX(lInd+(hInd-lInd)/2);
        // Divide into two arrays
        while (i <= j) {
            /**
             * In each iteration, we will identify a number from left side which
             * is greater then the pivot value, and also we will identify a number
             * from right side which is less then the pivot value. Once the search
             * is done, then we exchange both numbers.
             */
            while (modelo.getCoordenadaX(i) < pivot) {
                i++;
            }
            while (modelo.getCoordenadaX(j) > pivot) {
                j--;
            }
            if (i <= j) {
                modelo.exchangeNumbers(i, j);
                //move index to next position on both sides
                i++;
                j--;
            }
        }
        // call quickSort() method recursively
        if (lInd < j)
            ordenarPuntos(lInd, j);
        if (i < hInd)
            ordenarPuntos(i, hInd);
    }
}
