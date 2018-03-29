package Modelo.Piezas;

import Modelo.Pieza;

public class Torre extends Pieza{
    private static String NAME = "Dama";
    private static char VALUE = 'D';
    private static String PATH = "";

    public Torre() {
        super(NAME, VALUE, PATH);
    }
}
