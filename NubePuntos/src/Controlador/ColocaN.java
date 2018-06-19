package Controlador;

import Modelo.ModelInterface;

import java.util.ArrayList;

public class ColocaN implements ControlerInterface{

    private ModelInterface modelo;

    public ColocaN(ModelInterface modelo){
        this.modelo = modelo;
    }

    public void distanciaMinima(){
        ordenarPuntosPorX(0, modelo.getLenght() - 1); //Order by X O(log(n))
        int[] cercanos = obtenerCercanos(0, modelo.getLenght() - 1, (modelo.getLenght() - 1)/2);
        modelo.setCercanos(cercanos[0], cercanos[1]);
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

    private void ordenarPuntosPorX(int lInd, int hInd){
        int i = lInd;
        int j = hInd;
        // calculate pivot number, I am taking pivot as middle index number
        int pivot = modelo.getCoordenadaX(lInd+(hInd-lInd)/2);
        // Divide into two arrays
        while (i <= j) {
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
        if (lInd < j) {
            ordenarPuntosPorX(lInd, j);
        }
        if (i < hInd) {
            ordenarPuntosPorX(i, hInd);
        }
    }

    private int[] obtenerCercanos(int lInd, int hInd, int x){
        int length = hInd - lInd;
        if (length > 2){
            int[] mitadDer = obtenerCercanos(lInd, x, lInd+(x-lInd)/2);
            int[] mitadIzq = obtenerCercanos(x, hInd, x+(hInd-x)/2);

            int[] cercanos = minimo(mitadDer, mitadIzq);
            long distanciaMinima = distancia(cercanos[0], cercanos[1]);

            Integer[] candidatos = buscarCandidatos(lInd, hInd, x, distanciaMinima);
            for (int punto1 = 0; punto1 < candidatos.length; punto1++){
                for (int punto2 = 0; punto2 < candidatos.length; punto2++){
                    long dist = distancia(candidatos[punto1], candidatos[punto2]);
                    if (dist < distanciaMinima){
                        distanciaMinima = dist;
                        cercanos[0] = punto1;
                        cercanos[1] = punto2;
                    }
                }
            }
            return cercanos;
        } else {
            return new int[]{lInd, hInd};
        }
    }

    private int[] minimo(int[] par1, int[] par2){
        long d1 = distancia(par1[0], par1[1]);
        long d2 = distancia(par2[0], par2[1]);

        if (d1 == 0 && d2 == 0){
            return new int[]{par1[0], par2[0]};
        } else if (d1 == 0){
            return par2;
        } else if (d2 == 0){
            return par1;
        } else if (d1 < d2){
            return par1;
        } else if (d2 < d1){
            return par2;
        } else{
            return new int[]{0,0};
        }
    }

    private Integer[] buscarCandidatos(int lInd, int hInd, int x, long dMin){
        ArrayList<Integer> candidatos = new ArrayList<>();
        //buscar menores de X
        for(int punto = lInd; punto < x; punto++){
            if (modelo.getCoordenadaX(x) - modelo.getCoordenadaX(punto) < dMin){
                candidatos.add(punto);
            }
        }
        //buscar mayores de X
        for(int punto = hInd; punto > x; punto--){
            if (modelo.getCoordenadaX(punto) - modelo.getCoordenadaX(x) < dMin){
                candidatos.add(punto);
            }
        }
        Integer[] ret = new Integer[candidatos.size()];
        return candidatos.toArray(ret);
    }
}
