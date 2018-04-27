/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.Dimension;
import javax.swing.JDialog;

/**
 *
 * @author dbq560
 */
public class Mesa extends JDialog {
    public Mesa (int tamaño_tablero){
        this.setSize(new Dimension(tamaño_tablero * 50+15, (tamaño_tablero)* 50 + 40));
        this.setLocationRelativeTo(null);
    }
}
