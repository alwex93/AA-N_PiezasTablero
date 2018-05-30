package Vista;

import java.util.Objects;

public class Palabra {

    private int initPos, endPos;
    private String sustituto;

    public Palabra(int init, int end, String sustituto){
        initPos = init;
        endPos = end;
        this.sustituto = sustituto;
    }

    public int getInitPos() {
        return initPos;
    }

    public int getEndPos() {
        return endPos;
    }

    public String getSustituto() {
        return sustituto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Palabra palabra = (Palabra) o;
        return sustituto.equals(palabra.sustituto);
    }

    @Override
    public int hashCode() {

        return Objects.hash(initPos, endPos);
    }
}
