package Vista;

import Modelo.Datos;
import javax.swing.*;

public class Ventana extends JFrame implements ViewInterface{

    private Datos modelo;
    private final int DEFAULT = 10;

    public Ventana(){
        setLayout(null);
        setSize(640,460);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        modelo = new Datos(DEFAULT, PanelPintado.PX, PanelPintado.PY);
        PanelPintado table = new PanelPintado(modelo, 0, PanelControl.WINDOW_HEIGHT,getWidth() - 5, getHeight() - PanelControl.WINDOW_HEIGHT - 29);
        PanelControl p1 = new PanelControl(table, modelo, 0,0,getWidth());
        add(p1);
        add(table);
    }
    @Override
    public void createWindow() {
        setVisible(true);
    }
}
