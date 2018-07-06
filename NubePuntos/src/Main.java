import Controlador.ColocaN;
import Controlador.ControlerInterface;
import Modelo.Datos;
import Modelo.ModelInterface;
import Modelo.Punto;
import Vista.Ventana;
import Vista.ViewInterface;

public class Main {

    public static void main(String[] args){
        ViewInterface ventana = new Ventana();
        ventana.createWindow();
    }
}
