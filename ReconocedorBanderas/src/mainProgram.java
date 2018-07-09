import Modelo.Datos;
import Modelo.ModelInterface;
import Vista.Ventana;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class mainProgram {

    public static void main(String[] args){

        ModelInterface datos = new Datos();
        new Ventana(datos).abrirVentana();
    }

}
