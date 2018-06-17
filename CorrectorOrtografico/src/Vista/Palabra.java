package Vista;

import java.util.Objects;

public class Palabra {

    private int initPos, endPos;
    private String[] sustitutos;
    private String palabra;
    private boolean corregida;

    public Palabra(String palabra, int init, int end, String[] sustitutos){
        this.palabra = palabra;
        initPos = init;
        endPos = end;
        this.sustitutos = sustitutos;
        corregida = sustitutos == null;
    }

    public int getInitPos() {
        return initPos;
    }

    public int getEndPos() {
        return endPos;
    }

    public String[] getSustitutos() {
        return sustitutos;
    }

    public String getPalabra() {
        return palabra;
    }

    public void corregir(){
        corregida = true;
    }

    public boolean isCorregida(){
        return corregida;
    }

    public void actualizarPosicion(int diferencia){
        initPos += diferencia;
        endPos += diferencia;
    }

    public void quitarSignoPuntuacion(){
        endPos--;
    }

    public String getFirstSustituto(){
        return sustitutos != null ? sustitutos[0] : "";
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

        return Objects.hash(initPos, endPos);
    }

    @Override
    public String toString() {
        return palabra;
    }
}
