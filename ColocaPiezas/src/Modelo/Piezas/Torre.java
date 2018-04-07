package Modelo.Piezas;

import Modelo.Pieza;

public class Torre extends Pieza{
    private static String NAME = "Torre";
    private static char VALUE = 'T';
    private static String PATH = "";

    public Torre() {
        super(NAME, VALUE, PATH);
    }
}
