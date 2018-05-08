package Modelo.Piezas;

import Modelo.Pieza;

public class Lancero extends Pieza {

    public final static char VALUE = 'L';
    private static String NAME = "Lancero";
    private static String PATH = "Piezas/Lancero.png";

    public Lancero() {
        super(NAME, VALUE, PATH);
    }
}
