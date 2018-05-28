package Vista;

import Controlador.Controler;
import Controlador.InterfazControler;
import Modelo.InterfazModelo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.math.BigDecimal;

public class ResultPage extends JFrame{
    private JLabel tiempoEjecucion;
    private JLabel proporcion;
    private JTable dataTable;
    private JPanel panel;
    private JLabel lngAntes;
    private JLabel lngDespues;
    private JButton generarFicheroButton;
    private InterfazModelo modelo;
    private InterfazControler controler;

    public ResultPage(InterfazModelo modelo, File fichero){
        super("Resultado Compresión");
        this.modelo = modelo;
        controler = new Controler(this.modelo);
        init(fichero);
    }

    private void init(File fichero){
        Reloj reloj = new Reloj();
        setSize(480,218);
        if(modelo != null){
            generarFicheroButton.addActionListener(e -> {
                if(controler.generarFichero()){
                    generarFicheroButton.setBackground(Color.GREEN);
                } else {
                    generarFicheroButton.setBackground(Color.RED);
                }
            });
            reloj.Contar();
            proporcion.setText(redondear((long)controler.comprimirFichero(fichero)) + "%");
            lngAntes.setText(controler.getFileLenghtAntes());
            lngDespues.setText(controler.getFileLenghtDespues());
            reloj.Detener();
            chargeTableData();
            tiempoEjecucion.setText(reloj.getTiempo());
        }
        add(panel);
        //pack();
        setLocationRelativeTo(null);
    }

    private String redondear(long number){
        return new BigDecimal(number).setScale(2,BigDecimal.ROUND_HALF_UP).toString();
    }

    private void chargeTableData(){
        DefaultTableModel model = new DefaultTableModel(modelo.getTablaFrecuencias(), modelo.getColumnNames()){
            public boolean isCellEditable(int row, int column) {
                return false;//This causes all cells to be not editable
            }
            public Class<?> getColumnClass(int columnIndex) {
                if (getValueAt(0, columnIndex) == null) {
                    return Object.class;
                }
                return getValueAt(0, columnIndex).getClass();
            }
        };
        dataTable.setAutoCreateRowSorter(true);
        dataTable.setModel(model);
    }
}