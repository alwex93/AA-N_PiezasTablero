import Vista.Punto;
import Vista.Ventana;

import java.util.Random;

public class Main {

    public static void main(String[] args){
        Ventana v = new Ventana();
        Random rnd = new Random(1993);
        for (int i = 0; i < 100; i++){
            v.addPoint(new Punto(rnd.nextInt() , rnd.nextInt()));
        }
        v.nearedPoints(new Punto(30,30), new Punto(30,50));
        v.repaint();
    }
}
