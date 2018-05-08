package Controlador;

import Vista.Punto;

public class ColocaN {

    private Punto[] puntos;
    private Punto cercano1, cercano2;

    public ColocaN(){
    }

    public void distanciaMinima(Punto[] puntos){
        int masCercano;
        this.puntos = puntos;
        int distanciaMinima = Integer.MAX_VALUE;
        ordenarPuntos(0, puntos.length - 1); //Order by X O(log(n))
        for (Punto punto : puntos) {//O(n)
            masCercano = minim(punto, 0, puntos.length - 1);
            if (distanciaY(punto, masCercano) < distanciaMinima){
                distanciaMinima = distanciaY(punto, masCercano);
                cercano1 = punto;
                cercano2 = puntos[masCercano];
            }
        }
    }

    private void ordenarPuntos(int lInd, int hInd){
        int i = lInd;
        int j = hInd;
        // calculate pivot number, I am taking pivot as middle index number
        int pivot = puntos[lInd+(hInd-lInd)/2].getX();
        // Divide into two arrays
        while (i <= j) {
            /**
             * In each iteration, we will identify a number from left side which
             * is greater then the pivot value, and also we will identify a number
             * from right side which is less then the pivot value. Once the search
             * is done, then we exchange both numbers.
             */
            while (puntos[i].getX() < pivot) {
                i++;
            }
            while (puntos[j].getX() > pivot) {
                j--;
            }
            if (i <= j) {
                exchangeNumbers(i, j);
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

    private void exchangeNumbers(int i, int j) {
        Punto temp = puntos[i];
        puntos[i] = puntos[j];
        puntos[j] = temp;
    }

    private int minim(Punto elem, int i, int j){
        int minD, minI;
        if( j - i > 2){
            minI = minim(elem, i, i +(j - i)/2);
            minD = minim(elem, i +(j - i)/2, j);
            return getMinimo(elem, minI, minD);
        } else {
            return getMinimo(elem, i, j);
        }
    }

    private int getMinimo(Punto elem, int indx1, int indx2){
        if (distanciaY(elem, indx1) < distanciaY(elem, indx2)) {
            return indx1;
        } else {
            return indx2;
        }
    }

    private int distanciaY(Punto elem, int indx){
        if (elem.equals(puntos[indx])) return Integer.MAX_VALUE;
        return Math.abs(elem.getY() - puntos[indx].getY());
    }

    public String printMasCercanos(){
        if (cercano1 != null && cercano2 != null){
            return "P1: " + cercano1.toString() + "\nP2: " + cercano2.toString();
        } else{
            return "no hay";
        }
    }
}
