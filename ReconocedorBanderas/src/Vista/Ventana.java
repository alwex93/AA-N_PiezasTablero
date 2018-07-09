package Vista;

import Controlador.ControlerInterface;
import Controlador.ProbabilisticoMonteCarlo;
import Modelo.ModelInterface;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Ventana extends JFrame implements ViewInterface{
    private JComboBox<String> banderas;
    private JLabel banderaFile;
    private JSlider numPixeles;
    private JLabel banderaAdivinada;
    private JPanel mainPanel;
    private JLabel cantidadPixeles;
    private JComboBox<String> seleccionBanderas;
    private ModelInterface modelo;
    private ControlerInterface controler;

    public Ventana(ModelInterface modelo){
        this.modelo = modelo;
        this.controler = new ProbabilisticoMonteCarlo(modelo);
        setSize(520, 270);
        init();
        setVisible(false);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void init(){
        modelo.setImagenBlanca("Banderas\\imagenVacia.jpg");
        banderaAdivinada.setIcon(modelo.getImagenBlanca());
        banderaAdivinada.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        banderaFile.setIcon(modelo.getImagenBlanca());
        banderaFile.setBorder(BorderFactory.createLineBorder(Color.black, 2));

        seleccionBanderas.setModel(setCarpetas());
        seleccionBanderas.addActionListener(ev -> {
            int banderaSeleccionada = seleccionBanderas.getSelectedIndex() ;
            banderas.setModel(getImagenesCarpeta((String) seleccionBanderas.getSelectedItem()));
            banderaFile.setIcon(modelo.getBanderaImg(banderaSeleccionada));
            banderaAdivinada.setIcon(controler.adivinarImagen(banderaSeleccionada, numPixeles.getValue()));

        });

        //banderas.setModel(model);
        banderas.addActionListener(ev -> {
            int banderaSeleccionada = banderas.getSelectedIndex() - 1;
            if (banderaSeleccionada < 0){
                banderaFile.setIcon(modelo.getImagenBlanca());
                banderaAdivinada.setIcon(modelo.getImagenBlanca());
            } else {
                banderaFile.setIcon(modelo.getBanderaImg(banderaSeleccionada));
                banderaAdivinada.setIcon(controler.adivinarImagen(banderaSeleccionada, numPixeles.getValue()));
            }
        });






        numPixeles.addChangeListener(ev -> {
            cantidadPixeles.setText(numPixeles.getValue() + "%");
            int banderaSeleccionada = banderas.getSelectedIndex() - 1;
            banderaAdivinada.setIcon(controler.adivinarImagen(banderaSeleccionada, numPixeles.getValue()));
        });
        cantidadPixeles.setText(numPixeles.getValue() + "%");

        add(mainPanel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private DefaultComboBoxModel<String> getImagenesCarpeta(String nombreCarpeta){
        modelo.CambiarCarpeta("Bandera\\" + nombreCarpeta);
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("");

        File[] ficheros = new File(nombreCarpeta).listFiles();
        if (ficheros != null){
            for(String nombre : modelo.getNameBanderas()){
                model.addElement(nombre);
            }
        }
        return model;
    }

    private DefaultComboBoxModel<String> setCarpetas(){
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

        File[] ficheros = new File("Banderas").listFiles();
        if (ficheros != null){
            for(File directorioRegion : ficheros){
                if(directorioRegion.isDirectory()){
                    model.addElement(directorioRegion.getName());
                }
            }
        }
        return model;
    }

    @Override
    public void abrirVentana() {
        setVisible(true);
    }
}
