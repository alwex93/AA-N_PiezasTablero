package Vista;

import Controlador.Controler;
import Modelo.Datos;

import javax.swing.*;
import java.io.File;

public class MainPage extends JFrame{
    private JTextField textField1;
    private JButton comprimirButton;
    private JButton examinarButton;
    private JPanel mainPanel;
    private JFrame page = this;
    private Controler controlador;
    private Datos modelo;

    public MainPage() {
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
            modelo = new Datos();
            controlador = new Controler(modelo);
            String path = textField1.getText();
            File fichero = new File(path);
            if (fichero.exists()){
                new ResultPage(modelo, fichero).setVisible(true);
            }
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
