package Controlador;

import Modelo.Bandera;
import Modelo.ModelInterface;

public class ProbabilisticoMonteCarlo implements ControlerInterface{

    private ModelInterface modelo;

    public ProbabilisticoMonteCarlo(ModelInterface modelo){
        this.modelo = modelo;
    }

    @Override
    public Bandera adivinarImagen(int bandera, int porcentajePixeles){
       return modelo.cargarBanderaAdivinar(bandera, porcentajePixeles);
    }

}
