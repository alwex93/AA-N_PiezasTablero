package Modelo;

import java.awt.*;
import java.util.Objects;

public class Pixel {

    private int fila, columna;
    private int color;
    private Color colorBasico;

    public Pixel(int fila, int columna, int color){
        this.fila = fila;
        this.columna = columna;
        this.color = color;
    }

    public void setColorBasico(GammaColores paleta){
        //int clr=  image.getRGB(x,y);
        colorBasico = paleta.getPrimariColor(color);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pixel pixel = (Pixel) o;
        return fila == pixel.fila &&
                columna == pixel.columna;
    }

    @Override
    public int hashCode() {

        return Objects.hash(fila, columna);
    }
}
