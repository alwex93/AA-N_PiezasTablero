package Vista;

import javax.swing.*;

public class VentanaSustitucion extends JDialog{
    private JList<String> erroneas;
    private JTextField contexto;
    private JComboBox<String> opciones;
    private JPanel mainPanel;
    private JButton cambiarButton;
    private String textoFinal;

    private final int CONTEXT_LENGHT = 10;
    private static VentanaSustitucion ventana = null;

    public VentanaSustitucion(String texto, Palabra[] palabras){
        setTitle("Opciones Avanzadas");
        initComponents();
        setList(palabras);
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(360, 150);
        setResizable(false);

        textoFinal = texto;
        erroneas.addListSelectionListener(lse -> {
            if (!lse.getValueIsAdjusting() && erroneas.getSelectedIndex() != -1) {
                opciones.setVisible(true);
                setContexto(texto, palabras[erroneas.getSelectedIndex()]);
                setPosibles(palabras[erroneas.getSelectedIndex()]);
            }
        });
        add(mainPanel);
        ventana = this;
        cambiarButton.addActionListener(e -> {
            hacerCorreccion(palabras);
        });
    }

    private void hacerCorreccion(Palabra[] palabras){
        String sustituto = (String) opciones.getSelectedItem();
        if (sustituto != null){
            Palabra susituir = palabras[erroneas.getSelectedIndex()];
            textoFinal = textoFinal.substring(0, susituir.getInitPos()) + sustituto +
                    textoFinal.substring(susituir.getEndPos(), textoFinal.length());
            susituir.corregir();
            for(int pal = erroneas.getSelectedIndex(); pal < palabras.length; pal++){
                palabras[pal].actualizarPosicion(sustituto.length() - susituir.getPalabra().length());
            }
            setList(palabras);
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

    private void setList(Palabra[] palabras){
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

    public static VentanaSustitucion getWindow(String texto, Palabra[] palabras){
        if (ventana == null){
            ventana = new VentanaSustitucion(texto, palabras);
        }
        return ventana;
    }
}
