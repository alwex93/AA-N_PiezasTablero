package Modelo.Piezas;

import Modelo.Pieza;

public class Torre extends Pieza{

    public final static char VALUE = 'T';
    private static String NAME = "Torre";
    private static String PATH = "Piezas/Torre.png";

    public Torre() {
        super(NAME, VALUE, PATH);
    }
}
