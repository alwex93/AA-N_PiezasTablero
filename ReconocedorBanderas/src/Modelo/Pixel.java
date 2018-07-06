package Modelo;

import java.awt.*;

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
        int  red   = (color & 0x00ff0000) >> 16;
        int  green = (color & 0x0000ff00) >> 8;
        int  blue  =  color & 0x000000ff;
    }

}
