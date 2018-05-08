package Vista;

import javax.swing.*;
import java.awt.*;

public class PanelPintado extends JPanel{

    public PanelPintado(int origX, int origY, int width, int height){
        setBounds(origX, origY, width, height);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
    }
}
