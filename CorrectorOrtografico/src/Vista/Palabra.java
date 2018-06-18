package Vista;

import java.util.Objects;

public class Palabra {

    private int initPos;
    private String[] sustitutos;
    private String palabra;
    private boolean corregida;
    private int diferencia;

    public Palabra(String palabra, int init, String[] sustitutos){
        this.palabra = palabra;
        initPos = init;
        this.sustitutos = sustitutos;
        corregida = sustitutos == null;
        diferencia = 0;
    }

    public int getInitPos() {
        return initPos;
    }

    public int getEndPos() {
        return initPos + palabra.length() + diferencia;
    }

    public String[] getSustitutos() {
        return sustitutos;
    }

    public String getPalabra() {
        return palabra;
    }

    public String corregir(int sustituto){
        if (sustituto < sustitutos.length){
            corregida = true;
            diferencia = palabra.length() - sustitutos[sustituto].length();
            palabra = sustitutos[sustituto];
        }
        return palabra;
    }

    public boolean isCorregida(){
        return corregida;
    }

    public void actualizarPosicion(int diferencia){
        initPos += diferencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Palabra palabra = (Palabra) o;
        return palabra.palabra.equals(this.palabra);
    }

    @Override
    public int hashCode() {

        return Objects.hash(initPos);
    }

    @Override
    public String toString() {
        return palabra;
    }
}
