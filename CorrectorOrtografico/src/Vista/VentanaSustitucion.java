package Vista;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Arrays;

public class VentanaSustitucion extends JDialog{
    private JList<String> erroneas;
    private JTextField contexto;
    private JComboBox<String> opciones;
    private JPanel mainPanel;
    private JButton cambiarButton;
    private String textoFinal;
    private VentanaPrincipal parent;
    private ArrayList<Palabra> palabras;

    private final int CONTEXT_LENGHT = 10;
    private static VentanaSustitucion ventana = null;

    public VentanaSustitucion(VentanaPrincipal parent, String texto, Palabra[] palabras){
        this.parent = parent;
        this.palabras = new ArrayList<>();
        this.palabras.addAll(Arrays.asList(palabras));
        setTitle("Opciones Avanzadas");
        initComponents();
        setList(this.palabras);
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(360, 150);
        setResizable(false);

        textoFinal = texto;
        erroneas.addListSelectionListener(lse -> {
            if (!lse.getValueIsAdjusting() && erroneas.getSelectedIndex() != -1) {
                opciones.setVisible(true);
                setContexto(texto, this.palabras.get(erroneas.getSelectedIndex()));
                setPosibles(this.palabras.get(erroneas.getSelectedIndex()));
            }
        });
        add(mainPanel);
        ventana = this;
        cambiarButton.addActionListener(e -> {
            hacerCorreccion(this.palabras);
            parent.setText(textoFinal);
        });

        WindowListener exitListener = new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                parent.setText(textoFinal);
            }
        };
        addWindowListener(exitListener);
    }

    private void hacerCorreccion(ArrayList<Palabra> palabras){
        String sustituto = (String) opciones.getSelectedItem();
        if (sustituto != null){
            Palabra sustituir = palabras.remove(erroneas.getSelectedIndex());
            textoFinal = textoFinal.substring(0, sustituir.getInitPos()) + "<b color='green'>" + sustituto + "</b>"+
                    textoFinal.substring(sustituir.getEndPos(), textoFinal.length());
            sustituir.corregir(opciones.getSelectedIndex());
            for(int pal = erroneas.getSelectedIndex(); pal < palabras.size(); pal++){
                palabras.get(pal).actualizarPosicion(17 + sustituto.length() + 4 - sustituir.getPalabra().length());
            }
            setList(palabras);
            contexto.setText("");
            opciones.setModel(new DefaultComboBoxModel<>());
        }
    }

    private void initComponents(){
        contexto.setEditable(false);
        contexto.setText("");
        opciones.setVisible(false);
    }

    private void setContexto(String texto, Palabra pal){
        this.contexto.setText(
                texto.substring(getInitContext(texto, pal.getInitPos()), getEndContext(texto, pal.getEndPos())));
    }

    private int getInitContext(String texto, int initPal){
        int init = initPal - CONTEXT_LENGHT;
        if (init < 0) return 0;
        while (init > 0 && texto.charAt(init) != ' ') init--;
        return init;
    }

    private int getEndContext(String texto, int endPal){
        int init = endPal + CONTEXT_LENGHT;
        if (init > texto.length()) return texto.length();
        while (init < texto.length() && texto.charAt(init) != ' ') init++;
        return init;
    }

    private void setList(ArrayList<Palabra> palabras){
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Palabra palabra : palabras) {
            if(!palabra.isCorregida()){
                listModel.addElement(palabra.getPalabra());
            }
        }
        erroneas.setModel(listModel);
        erroneas.setLayoutOrientation(JList.VERTICAL);
        erroneas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void setPosibles(Palabra palabra){
        DefaultComboBoxModel<String> listModel = new DefaultComboBoxModel<>();
        for (String sustitutos : palabra.getSustitutos()) {
            listModel.addElement(sustitutos);
        }
        opciones.setModel(listModel);
    }

    public static VentanaSustitucion getWindow(VentanaPrincipal parent, String texto, Palabra[] palabras){
        if (ventana == null){
            ventana = new VentanaSustitucion(parent, texto, palabras);
        }
        return ventana;
    }
}
