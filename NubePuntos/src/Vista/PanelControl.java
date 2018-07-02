package Vista;

import Controlador.ColocaN;
import Controlador.ColocaN2;
import Controlador.ControlerInterface;
import Modelo.Datos;
import Modelo.ModelInterface;
import javax.swing.*;
import java.awt.*;

public class PanelControl extends JPanel{
    private JButton sol1, sol2, genPoints;
    private JTextField puntos;
    private ModelInterface modelo;
    private ControlerInterface controlerOpt;
    private PanelPintado paintPanel;
    private JLabel tiempoEjecucion, labelTiempo, labelCercanos, infoCercanos;
    private Reloj reloj;

    private final int BUTTON_HEIGHT = 20;
    private final int BUTTON_WIDTH = 80;
    private final int BUTTON_SEP = 5;
    public static final int WINDOW_HEIGHT = 45;

    public PanelControl(PanelPintado drawPanel, Datos modelo, int origX, int origY, int width){
        setBounds(origX, origY, width, WINDOW_HEIGHT);
        setBackground(Color.CYAN);
        setVisible(true);
        init();
        this.modelo = modelo;
        paintPanel = drawPanel;
    }

    private void init(){
        int initButtonX = BUTTON_SEP;
        this.setLayout(null);

        sol1 = new JButton("SOL1");
        sol2 = new JButton("SOL2");
        genPoints = new JButton("GenerarPuntos");
        puntos = new JTextField("10");
        labelTiempo = new JLabel("Tiempo de ejecución:");
        tiempoEjecucion = new JLabel(" ");
        labelCercanos = new JLabel("Puntos cercanos:");
        infoCercanos = new JLabel("");

        sol1.setBounds(initButtonX,BUTTON_SEP,BUTTON_WIDTH, BUTTON_HEIGHT);
        initButtonX += BUTTON_WIDTH + BUTTON_SEP;
        sol2.setBounds(initButtonX,BUTTON_SEP,BUTTON_WIDTH, BUTTON_HEIGHT);
        initButtonX += BUTTON_WIDTH + BUTTON_SEP;
        puntos.setBounds(initButtonX,BUTTON_SEP,BUTTON_WIDTH, BUTTON_HEIGHT);
        initButtonX += BUTTON_WIDTH + BUTTON_SEP;
        genPoints.setBounds(initButtonX,BUTTON_SEP,BUTTON_WIDTH + 60, BUTTON_HEIGHT);

        initButtonX = BUTTON_SEP;
        int initButtonY = BUTTON_SEP + BUTTON_HEIGHT;
        labelTiempo.setBounds(initButtonX,initButtonY,BUTTON_WIDTH + 55, BUTTON_HEIGHT);
        initButtonX += BUTTON_WIDTH + BUTTON_SEP + 38;
        tiempoEjecucion.setBounds(initButtonX,initButtonY,BUTTON_WIDTH, BUTTON_HEIGHT);
        initButtonX += BUTTON_WIDTH + BUTTON_SEP;
        labelCercanos.setBounds(initButtonX,initButtonY,BUTTON_WIDTH + 20, BUTTON_HEIGHT);
        initButtonX += BUTTON_WIDTH + 20 + BUTTON_SEP;
        infoCercanos.setBounds(initButtonX,initButtonY,BUTTON_WIDTH + 200, BUTTON_HEIGHT);

        sol1.addActionListener(e -> {
            sol1.setBackground(Color.GREEN);
            sol2.setBackground(UIManager.getColor("Button.background"));
            controlerOpt = new ColocaN(modelo);
            reloj = new Reloj();
            reloj.Contar();
            controlerOpt.distanciaMinima();
            reloj.Detener();
            paintPanel.repaint();
            tiempoEjecucion.setText(reloj.getTiempo());
            infoCercanos.setText(modelo.getInfoCercanos());
        });

        sol2.addActionListener(e -> {
            sol2.setBackground(Color.GREEN);
            sol1.setBackground(UIManager.getColor("Button.background"));
            controlerOpt = new ColocaN2(modelo);
            reloj = new Reloj();
            reloj.Contar();
            controlerOpt.distanciaMinima();
            reloj.Detener();
            paintPanel.repaint();
            tiempoEjecucion.setText(reloj.getTiempo());
            infoCercanos.setText(modelo.getInfoCercanos());
        });

        genPoints.addActionListener(e -> {
            tiempoEjecucion.setText("");
            infoCercanos.setText("");
            sol1.setBackground(UIManager.getColor("Button.background"));
            sol2.setBackground(UIManager.getColor("Button.background"));
            modelo.generarPuntos(Integer.parseInt(puntos.getText()), paintPanel.PX, paintPanel.PY);
            paintPanel.repaint();
        });

        add(sol1);
        add(sol2);
        add(puntos);
        add(genPoints);
        add(labelTiempo);
        add(tiempoEjecucion);
        add(labelCercanos);
        add(infoCercanos);
    }
}