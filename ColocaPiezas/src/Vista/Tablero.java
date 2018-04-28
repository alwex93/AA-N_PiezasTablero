package Vista;

import javax.swing.*;
import java.awt.*;

public class Tablero extends JPanel{

    Image[][] tablero;

    public Tablero(){
    }


    @Override
    public void paint (Graphics g){
        pintarTablero(g);
        pintarPiezas(g);
        g.dispose();
    }

    private void pintarTablero (Graphics g){
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j< tablero.length ; j++) {
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
        for (int columna = 0; columna < tablero.length; columna++) {
            for (int fila = 0; fila < tablero.length; fila++) {
                pieza = tablero[fila][columna];
                if (pieza != null){
                    g.drawImage(pieza, columna * 50, fila * 50, this);
                }
            }
        }
    }

    public void setTablero(Image[][] tablero) {
        this.tablero = tablero;
    }
}
