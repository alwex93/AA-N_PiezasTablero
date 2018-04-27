package Modelo;

import javax.swing.*;
import java.awt.*;

public abstract class Pieza {

    private char value;
    private String name;
    private String img;
    private boolean colocada;


    public Pieza(String name, char value, String relativePath){
        this.name = name;
        this.value = value;
        img = relativePath;
        colocada = false;
    }

    public char getValue(){
        return value;
    }

    public void colocar() {
        this.colocada = true;
    }

    public void quitar() {
        this.colocada = false;
    }

    public boolean isColocada() {
        return colocada;
    }

    public Image getImage(){
        return new ImageIcon(img).getImage();
    }

}
