package Vista;

import Modelo.ModelInterface;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame implements ViewInterface{
    private JComboBox<String> banderas;
    private JLabel banderaFile;
    private JSlider numPixeles;
    private JLabel BanderaAdivinada;
    private JPanel mainPanel;
    private JLabel cantidadPixeles;
    private ModelInterface modelo;

    public Ventana(ModelInterface modelo){
        this.modelo = modelo;
        setSize(500, 270);
        init();
        setVisible(false);
        setLocationRelativeTo(null);
    }

    private void init(){
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for(String nombreBandera : modelo.getNameBanderas()){
            model.addElement(nombreBandera);
        }
        banderas.setModel(model);
        BanderaAdivinada.setIcon(modelo.getImagenBlanca());
        BanderaAdivinada.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        banderaFile.setIcon(modelo.getImagenBlanca());
        banderaFile.setBorder(BorderFactory.createLineBorder(Color.black, 2));

        banderas.addActionListener(ev -> {
            banderaFile.setIcon(modelo.getBanderaImg(banderas.getSelectedIndex()));
        });

        numPixeles.addChangeListener(ev -> {
            cantidadPixeles.setText(numPixeles.getValue() + "%");
        });
        cantidadPixeles.setText(numPixeles.getValue() + "%");

        add(mainPanel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void abrirVentana() {
        setVisible(true);
    }
}
