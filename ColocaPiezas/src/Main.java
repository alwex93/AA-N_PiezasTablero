
import Controlador.ControlInterface;
import Controlador.Controler;
import Modelo.PackPiezas;

public class Main {

    public static void main(String[] args) {
        ControlInterface controler = new Controler();
        PackPiezas cajaPiezas = new PackPiezas(8, 0, 0, 0, 0);
        System.out.println(controler.colocarPiezas(cajaPiezas.getPiezas()));
    }
}
