package Modelo.Piezas;

import Modelo.Pieza;

public class Alfil extends Pieza{

    public final static char VALUE = 'A';
    private static String NAME = "Alfil";
    private static String PATH = "Piezas/Alfil.png";

    public Alfil() {
        super(NAME, VALUE, PATH);
    }
}
