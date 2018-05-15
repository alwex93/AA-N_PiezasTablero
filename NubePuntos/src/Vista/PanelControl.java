package Vista;

import Controlador.ColocaN;
import Modelo.Datos;

import javax.swing.*;
import java.awt.*;

public class PanelControl extends JPanel{
    private JButton sol1, sol2, nuevosPuntos;
    private JTextField puntos;
    private Datos modelo;
    private ColocaN controlerOpt;
    private PanelPintado paintPanel;
    private JLabel tiempoEjecucion;
    private JLabel labelTiempo;
    private Reloj reloj;

    private final int BUTTON_HEIGHT = 20;
    private final int BUTTON_WIDTH = 80;
    private final int BUTTON_SEP = 5;
    public static final int WINDOW_HEIGHT = 30;

    public PanelControl(PanelPintado drawPanel, Datos modelo, int origX, int origY, int width){
        setBounds(origX, origY, width, WINDOW_HEIGHT);
        setBackground(Color.BLUE);
        setVisible(true);
        init();
        this.modelo = modelo;
        controlerOpt = new ColocaN(modelo);
        paintPanel = drawPanel;
    }

    private void init(){
        int initButtonX = BUTTON_SEP;
        this.setLayout(null);

        sol1 = new JButton("SOL1");
        sol2 = new JButton("SOL2");
        nuevosPuntos = new JButton("Nuevos Puntos");
        puntos = new JTextField("10");
        labelTiempo = new JLabel("Tiempo de ejecución:");
        tiempoEjecucion = new JLabel("hola");


        sol1.setBounds(initButtonX,BUTTON_SEP,BUTTON_WIDTH, BUTTON_HEIGHT);
        initButtonX += BUTTON_WIDTH + BUTTON_SEP;
        sol2.setBounds(initButtonX,BUTTON_SEP,BUTTON_WIDTH, BUTTON_HEIGHT);
        initButtonX += BUTTON_WIDTH + BUTTON_SEP;
        puntos.setBounds(initButtonX,BUTTON_SEP,BUTTON_WIDTH, BUTTON_HEIGHT);
        initButtonX += BUTTON_WIDTH + BUTTON_SEP;
        nuevosPuntos.setBounds(initButtonX,BUTTON_SEP,BUTTON_WIDTH + 40, BUTTON_HEIGHT);
        initButtonX += BUTTON_WIDTH + BUTTON_SEP + 40;
        labelTiempo.setBounds(initButtonX,BUTTON_SEP,BUTTON_WIDTH + 55, BUTTON_HEIGHT);
        initButtonX += BUTTON_WIDTH + BUTTON_SEP + 38;
        tiempoEjecucion.setBounds(initButtonX,BUTTON_SEP,BUTTON_WIDTH, BUTTON_HEIGHT);

        sol1.addActionListener(e -> {
            reloj = new Reloj();
            reloj.Contar();
            controlerOpt.distanciaMinima();
            reloj.Detener();
            paintPanel.repaint();
            tiempoEjecucion.setText(reloj.getTiempo());
        });

        nuevosPuntos.addActionListener(e -> {
            modelo.generarPuntos(Integer.parseInt(puntos.getText()), paintPanel.PX, paintPanel.PY);
            paintPanel.repaint();
        });

        add(sol1);
        add(sol2);
        add(puntos);
        add(nuevosPuntos);
        add(labelTiempo);
        add(tiempoEjecucion);
    }
}