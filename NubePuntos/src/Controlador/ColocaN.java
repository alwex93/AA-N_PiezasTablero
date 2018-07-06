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
        int[] cercanos = obtenerCercanos(0, modelo.getLenght() - 1);
        modelo.setCercanos(cercanos[0], cercanos[1]);
        modelo.printInfoPuntos();
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

    private int[] obtenerCercanos(int lInd, int hInd){
        int x = (lInd + hInd)/2;
        int length = hInd - lInd + 1;
        if (length > 3){
            int[] mitadIzq = obtenerCercanos(lInd, x);
            int[] mitadDer = obtenerCercanos(x + 1, hInd);

            int[] cercanos = minimo(mitadDer, mitadIzq);
            long distanciaMinima = distancia(cercanos[0], cercanos[1]);

            Integer[] candidatos = buscarCandidatos(lInd, hInd, x, distanciaMinima);
            for (int punto1 = 0; punto1 < candidatos.length - 1; punto1++){
                for (int punto2 = punto1 + 1; punto2 < candidatos.length; punto2++){
                    long dist = distancia(candidatos[punto1], candidatos[punto2]);
                    if (dist < distanciaMinima){
                        distanciaMinima = dist;
                        cercanos[0] = candidatos[punto1];
                        cercanos[1] = candidatos[punto2];
                    }
                }
            }
            return cercanos;
        } else if (length == 3){
            int[] parM = minimo(new int[]{lInd, lInd + 1}, new int[]{lInd + 1, hInd});
            return minimo(parM, new int[]{lInd, hInd});
        } else {
            return new int[]{lInd, hInd};
        }
    }

    private int[] minimo(int[] par1, int[] par2){
        long d1 = distancia(par1[0], par1[1]);
        long d2 = distancia(par2[0], par2[1]);

        if (d1 == Long.MAX_VALUE && d2 == Long.MAX_VALUE){
            return new int[]{par1[0], par2[0]};
        } else if (d1 == Long.MAX_VALUE){
            return par2;
        } else if (d2 == Long.MAX_VALUE){
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

        //lInd <-- X (X included)
        for (int punto = x; punto > lInd && estaDentroDmin(x, punto, dMin); punto--) candidatos.add(punto);
        //X ++> hInd (X excluded)
        for (int punto = x + 1; punto < hInd && estaDentroDmin(x, punto, dMin); punto++) candidatos.add(punto);
        Integer[] ret = new Integer[candidatos.size()];
        return candidatos.toArray(ret);
    }

    private boolean estaDentroDmin(int puntoMedio, int punto, long dMin){
        return modelo.getCoordenadaX(puntoMedio) - modelo.getCoordenadaX(punto) < dMin;
    }
}
