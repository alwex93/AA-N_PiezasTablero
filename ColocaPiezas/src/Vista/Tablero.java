package Vista;

import Modelo.ModelInterface;
import Modelo.Posicion;

import javax.swing.*;
import java.awt.*;

public class Tablero extends JPanel{

    ModelInterface modelo;

    public Tablero(ModelInterface bd){
        modelo = bd;
    }


    @Override
    public void paint (Graphics g){
        pintarTablero(g);
        pintarPiezas(g);
        g.dispose();
    }
    private void pintarTablero (Graphics g){
        for (int i = 0; i < modelo.getLenght(); i++) {
            for (int j = 0;j< modelo.getLenght() ; j++) {
                if((i+j)%2 == 0){
                    g.setColor(Color.WHITE);
                }else{
                    g.setColor(Color.BLACK);
                }
                g.drawRect(j*50, i*50, 50, 50);
                g.fillRect(j*50, i*50, 50, 50);
            }
        }
    }
    private void pintarPiezas(Graphics g) {
        Image pieza;
        for (int i = 0; i < modelo.getLenght(); i++) {
            for (int j = 0; j < modelo.getLenght(); j++) {
                pieza = modelo.getSquare(new Posicion(i, j));
                if (pieza != null){
                    g.drawImage(pieza, i * 50, j * 50, this);
                }
            }
        }
    }
}
