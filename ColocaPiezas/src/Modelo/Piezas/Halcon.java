package Modelo.Piezas;

import Modelo.Pieza;

public class Halcon extends Pieza{

    public final static char VALUE = 'H';
    private static String NAME = "Halcon";
    private static String PATH = "Piezas/Halcon.png";

    public Halcon() {
        super(NAME, VALUE, PATH);
    }
}
