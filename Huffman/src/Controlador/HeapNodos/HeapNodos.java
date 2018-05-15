package Controlador.HeapNodos;

import java.util.Comparator;
import java.util.PriorityQueue;

public class HeapNodos {
    private PriorityQueue<Nodo> cola;

    public HeapNodos(int size) {
        Comparator<Nodo> comparator = new ComparadorNodos();
        cola = new PriorityQueue<>(size, comparator);
    }

    public void addNodo(Nodo nuevo){
        cola.add(nuevo);
    }

    public Nodo[] getPrimeros(){
        return new Nodo[] {cola.poll(), cola.poll()};
    }

    public Nodo getNodo(){
        return cola.poll();
    }

    public boolean isEmpty(){
        return cola.isEmpty();
    }

    public boolean quedanNodos(){
        return cola.size() > 1;
    }

}
