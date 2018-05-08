/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.HeapNodos;

public class Nodo {
    private byte value;
    private int frecuencia;
    private Nodo cero, uno;
    
    public Nodo(byte value, int frecuencia) {
        this.value = value;
        this.frecuencia = frecuencia;
        cero = uno = null;
    }

    public int compareTo(Nodo o) {
        return Integer.compare(getCompareValue(), o.getCompareValue());
    }

    public int getCompareValue(){
        return frecuencia;
    }

    public void setCero(Nodo cero){
        this.cero = cero;
    }

    public void setUno(Nodo uno){
        this.uno = uno;
    }

    public Nodo getCero() {
        return cero;
    }

    public Nodo getUno() {
        return uno;
    }

    @Override
    public String toString() {
        return "Nodo{" +
                ", valor=" + value +
                ", frecuencia=" + frecuencia +
                ", cero=" + cero.value +
                ", uno=" + uno.value +
                '}';
    }
}


