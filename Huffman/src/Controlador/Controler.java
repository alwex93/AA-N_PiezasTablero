package Controlador;

import java.io.*;

import Controlador.HeapNodos.HeapNodos;
import Controlador.HeapNodos.Nodo;
import Modelo.InterfazModelo;
import org.apache.commons.io.IOUtils;

public class Controler implements InterfazControler{

    private InterfazModelo modelo;
    private HeapNodos lista;

    public Controler(InterfazModelo modelo){
        this.modelo = modelo;
    }

    public void readFile(File fichero){
        FileReader reader;
        byte[] bytes;
        try{
            reader = new FileReader(fichero);
            bytes = IOUtils.toByteArray(reader, "UTF8");
            for (byte b : bytes) {
                modelo.addSimbolo(b);
            }
            reader.close();
        }catch (IOException e){
            System.out.println("error");
        }
        modelo.showFrecuencias();
    }

    public void generarArbolHuffman(){
        int sizeTable = modelo.sizeTable();
        //ordenarSimbolos(0, sizeTable - 1); //O(log n)
        //generamos la lista ordenada de menor a mayor
        lista = new HeapNodos(sizeTable);
        for (int it = 0; it < sizeTable; it++){
            lista.addNodo(new Nodo(modelo.getValue(it), modelo.getFrecuencia(it)));
        }
        do{
            Nodo[] primeros = lista.getPrimeros();
            if (primeros[0] != null && primeros[1] != null){
                Nodo nuevo = new Nodo((byte)0, primeros[0].getCompareValue() + primeros[1].getCompareValue());
                nuevo.setCero(primeros[0]);
                nuevo.setUno(primeros[1]);
                lista.addNodo(nuevo);
            }
        } while(!lista.isEmpty());

    }
/*
    private void ordenarSimbolos(int lInd, int hInd){
        int i = lInd;
        int j = hInd;
        // calculate pivot number, I am taking pivot as middle index number
        int pivot = modelo.getFrecuencia(lInd+(hInd-lInd)/2);
        // Divide into two arrays
        while (i <= j) {
            /**
             * In each iteration, we will identify a number from left side which
             * is greater then the pivot value, and also we will identify a number
             * from right side which is less then the pivot value. Once the search
             * is done, then we exchange both numbers.
             *
            while (modelo.getFrecuencia(i) < pivot) {
                i++;
            }
            while (modelo.getFrecuencia(j) > pivot) {
                j--;
            }
            if (i <= j) {
                modelo.changeElement(i, j);
                //move index to next position on both sides
                i++;
                j--;
            }
        }
        // call quickSort() method recursively
        if (lInd < j)
            ordenarSimbolos(lInd, j);
        if (i < hInd)
            ordenarSimbolos(i, hInd);
    }*/

}
