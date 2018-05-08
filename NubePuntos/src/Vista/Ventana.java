package Vista;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Ventana extends JFrame implements ViewInterface{

    private int origX, maxX;
    private int origY, maxY;
    private ArrayList<Vista.Punto> puntos;
    private Vista.Punto p1, p2;

    private final int RADIO = 5;

    public Ventana(){
        setLayout(null);
        setSize(640,460);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        PanelControl p1 = new PanelControl(0,0,getWidth());
        PanelPintado table = new PanelPintado(0, p1.WINDOW_HEIGHT,getWidth() - 5, getHeight() - p1.WINDOW_HEIGHT - 29);
        add(p1);
        add(table);
        puntos = new ArrayList<>();
        origX = 7;
        maxX = getWidth() - 10;
        origY = p1.WINDOW_HEIGHT + 30;
        maxY = getHeight() - p1.WINDOW_HEIGHT - 29;
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        int x, y;
        for (Vista.Punto point : puntos) {
            x = origX + point.getX();
            y = origY + point.getY();
            g2d.fillOval(x,y,RADIO,RADIO);
            System.out.println("P(" + x + "," + y + ")");
        }
        if(p1 != null && p2 != null){
            g2d.setColor(Color.BLACK);
            x = origX + p1.getX();
            y = origY + p1.getY();
            g2d.fillOval(x,y,RADIO,RADIO);

            x = origX + p2.getX();
            y = origY + p2.getY();
            g2d.fillOval(x,y,RADIO,RADIO);
        }
        g.dispose();
    }

    @Override
    public void addPoint(Vista.Punto point) {
        point.setX(Math.abs(point.getX() + origX)%(maxX - 8));
        point.setY(Math.abs(point.getY() + origY)%(maxY - 10));
        if (!puntos.contains(new Vista.Punto(point.getX(), point.getY()))){
            puntos.add(point);
        }
    }

    @Override
    public void nearedPoints(Vista.Punto a, Vista.Punto b) {
        if (a != null && b != null){
            a.setX(Math.abs(a.getX() + origX)%(maxX - 8));
            a.setY(Math.abs(a.getY() + origY)%(maxY - 10));
            p1 = a;
            b.setX(Math.abs(b.getX() + origX)%(maxX - 8));
            b.setY(Math.abs(b.getY() + origY)%(maxY - 10));
            p2 = b;
        }
    }

    @Override
    public void cleanPoints() {
        puntos.clear();
    }
}
