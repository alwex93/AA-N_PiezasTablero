package Controlador;

import Modelo.ModelInterface;
import javax.swing.Icon;

public class ProbabilisticoMonteCarlo implements ControlerInterface{

    private ModelInterface modelo;

    public ProbabilisticoMonteCarlo(ModelInterface modelo){
        this.modelo = modelo;
    }

    @Override
    public Icon adivinarImagen(int bandera, int porcentajePixeles){
       return modelo.cargarBanderaAdivinar(bandera, porcentajePixeles);
    }

}
