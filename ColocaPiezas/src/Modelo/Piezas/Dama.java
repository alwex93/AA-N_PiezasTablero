package Modelo.Piezas;

import Modelo.Pieza;

public class  Dama extends Pieza{

    public final static char VALUE = 'D';
    private static String NAME = "Dama";
    private static String PATH = "Piezas/Dama.png";

    public Dama() {
        super(NAME, VALUE, PATH);
    }
}
