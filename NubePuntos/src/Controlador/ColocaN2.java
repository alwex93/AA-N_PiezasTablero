package Controlador;

import Modelo.Datos;
import Modelo.ModelInterface;

public class ColocaN2 implements ControlerInterface{

    private ModelInterface modelo;

    public ColocaN2(ModelInterface modelo){
        this.modelo = modelo;
        modelo.printInfoPuntos();
    }

    @Override
    public void distanciaMinima() {
        int cercano1 = 0, cercano2 = 0;
        long distanciaMinima = Long.MAX_VALUE;
        for (int punto1 = 0; punto1 < modelo.getLenght(); punto1++){
            for (int punto2 = 0; punto2 < modelo.getLenght(); punto2++){
                if (distancia(punto1, punto2) < distanciaMinima){
                    distanciaMinima = distancia(punto1, punto2);
                    cercano1 = punto1;
                    cercano2 = punto2;
                }
            }
        }
        modelo.setCercanos(cercano1, cercano2);
    }

    private long distancia(int elem, int indx){
        if (modelo.isEquals(elem, indx)) return Long.MAX_VALUE;
        return modelo.distancia(elem, indx);
    }
}
