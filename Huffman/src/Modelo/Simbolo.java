package Modelo;

import java.util.Objects;

/**
 * Created by Andrés Ramos Seguí.
 */
public class Simbolo {
    private byte valor_ascii;
    private int frequencia;
    private String haffmanValue;

    public Simbolo(int valor_ascii, int frequencia) {
        this.valor_ascii = (byte) valor_ascii;
        this.frequencia = frequencia;
        haffmanValue = "";
    }

    public Simbolo(int valor_ascii) {
        this.valor_ascii = (byte) valor_ascii;
        this.frequencia = 1;
        haffmanValue = "";
    }

    public void addUnoMas(){
        this.frequencia += 1;
    }

    public byte getValor_ascii() {
        return valor_ascii;
    }

    public int getFrequencia() {
        return frequencia;
    }

    public String getHaffmanValue() {
        return haffmanValue;
    }

    public void setHaffmanValue(String haffmanValue) {
        this.haffmanValue = haffmanValue;
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
                ", letra=" + (char) valor_ascii +
                '}';
    }
}
