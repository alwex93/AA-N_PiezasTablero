package Vista;

import Modelo.Datos;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class PanelPintado extends JPanel{

    private int origX;
    private int origY;
    private Datos modelo;

    private final int RADIO = 5;
    public static final int PX = 621, PY = 384;

    public PanelPintado(Datos modelo, int origX, int origY, int width, int height){
        setBounds(origX, origY, width, height);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        this.modelo = modelo;
        this.origX = 4;
        this.origY = 4;
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        for (int punto = 0; punto < modelo.getLenght(); punto++){
            printPunto(g2d, modelo.getCoordenadaX(punto), modelo.getCoordenadaY(punto));
        }
        if(modelo.hayCercanos()){
            g.setColor(Color.GREEN);
            printPunto(g2d, modelo.getPrimerCercanoX(), modelo.getPrimerCercanoY());
            printPunto(g2d, modelo.getSegundoCercanoX(), modelo.getSegundoCercanoY());
        }
        g.dispose();
    }

    private void printPunto(Graphics2D g2d, int x, int y){
        g2d.fillOval(normalizarX(x),normalizarY(y),RADIO,RADIO);
    }

    private int normalizarX(int x){
        return origX + x;
    }

    private int normalizarY(int y){
        return origY + y;
    }
}
