package Vista;

import javax.swing.*;
import java.awt.*;

public class PanelControl extends JPanel{
    private JButton sol1, sol2;

    private final int BUTTON_HEIGHT = 20;
    private final int BUTTON_WIDTH = 80;
    private final int BUTTON_SEP = 5;
    public final int WINDOW_HEIGHT = 30;

    public PanelControl(int origX, int origY, int width){
        init();
        setBounds(origX, origY, width, WINDOW_HEIGHT);
        setBackground(Color.BLUE);
        setVisible(true);
    }

    private void init(){
        this.setLayout(null);

        sol1 = new JButton("SOL1");
        sol2 = new JButton("SOL2");

        sol1.setBounds(BUTTON_SEP,BUTTON_SEP,BUTTON_WIDTH, BUTTON_HEIGHT);
        sol2.setBounds(BUTTON_SEP + BUTTON_WIDTH + BUTTON_SEP,BUTTON_SEP,BUTTON_WIDTH, BUTTON_HEIGHT);

        this.add(sol1);
        this.add(sol2);
    }
}