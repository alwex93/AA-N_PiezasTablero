package Modelo;

import java.util.Objects;

/**
 * Created by Andrés Ramos Seguí.
 */
public class Simbolo {
    private byte valor_ascii;
    private int frequencia;

    public Simbolo(int valor_ascii, int frequencia) {
        this.valor_ascii = (byte) valor_ascii;
        this.frequencia = frequencia;
    }

    public Simbolo(int valor_ascii) {
        this.valor_ascii = (byte) valor_ascii;
        this.frequencia = 1;
    }

    public void addUnoMas(){
        this.frequencia += 1;
    }

    public int getValor_ascii() {
        return valor_ascii;
    }

    public void setValor_ascii(int valor_ascii) {
        this.valor_ascii = (byte) valor_ascii;
    }

    public int getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(int frequencia) {
        this.frequencia = frequencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Simbolo simbolo = (Simbolo) o;
        return valor_ascii == simbolo.valor_ascii;
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor_ascii);
    }

    @Override
    public String toString() {
        return "Simbolo{" +
                "valor_ascii=" + valor_ascii +
                ", frequencia=" + frequencia +
                ", letra=" + (char) valor_ascii;
    }
}
