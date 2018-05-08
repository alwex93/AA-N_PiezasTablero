package Controlador.HeapNodos;

import java.util.Comparator;

public class ComparadorNodos implements Comparator<Nodo> {
    @Override
    public int compare(Nodo o1, Nodo o2) {
        return o1.compareTo(o2);
    }
}
