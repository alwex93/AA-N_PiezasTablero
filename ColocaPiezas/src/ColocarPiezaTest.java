import Modelo.Piezas.*;
import Modelo.Posicion;
import Modelo.Tablero;

public class ColocarPiezaTest {

    public static void main(String[] a){
        Tablero t = new Tablero(8);
        t.colocarPieza(new Posicion(3,3), new Halcon());
        t.print();
    }
}
