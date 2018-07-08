import Modelo.Datos;
import Modelo.ModelInterface;
import Vista.Ventana;

public class mainProgram {

    public static void main(String[] args){
        ModelInterface datos = new Datos("Imagenes");
        new Ventana(datos).abrirVentana();
    }

}
