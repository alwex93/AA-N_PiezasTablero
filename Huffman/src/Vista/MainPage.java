package Vista;

import Controlador.Controler;
import Modelo.Datos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MainPage extends JFrame{
    private JTextField textField1;
    private JButton comprimirButton;
    private JButton examinarButton;
    private JPanel mainPanel;
    private JFrame page = this;
    private Controler controlador;
    private Datos modelo;

    public MainPage() {
        modelo = new Datos();
        controlador = new Controler(modelo);
        init();
        examinarButton.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new File (System.getProperty("user.dir")));

            int seleccion = fc.showOpenDialog(page);

            if(seleccion == JFileChooser.APPROVE_OPTION){
                File fichero = fc.getSelectedFile();
                textField1.setText(fichero.getAbsolutePath());

            }
        });
        comprimirButton.addActionListener(e -> {
            String path = textField1.getText();
            File fichero = new File(path);
            if (fichero.exists()){
                controlador.readFile(fichero);
            }
            new ResultPage(modelo).setVisible(true);
        });
    }

    private void init(){
        setTitle("Compresor");
        setSize(640, 480);
        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
