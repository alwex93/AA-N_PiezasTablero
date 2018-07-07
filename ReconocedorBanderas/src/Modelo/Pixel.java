package Modelo;

import java.awt.*;
import java.util.Objects;

public class Pixel {

    private int rgb;
    private Color colorBasico;

    public Pixel(int color){
        rgb = color;
    }

    public Color getColorBasico(GammaColores paleta){
        return colorBasico = paleta.getPrimariColor(rgb);
    }

    public int getRGB(){
        return rgb;
    }

    public Color getColor(){
        return colorBasico;
    }
}
