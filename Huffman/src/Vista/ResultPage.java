package Vista;

import Modelo.Datos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ResultPage extends JFrame{
    private JLabel tiempoEjecucion;
    private JLabel proporcion;
    private Datos modelo;
    private JTable dataTable;
    private JPanel panel;

    public ResultPage(Datos modelo){
        super("Resultado Compresión");
        this.modelo = modelo;
        init();
    }

    private void init(){
        setSize(640,480);
        if(modelo != null){
            chargeTableData();
        }
        add(panel);
        pack();
        setLocationRelativeTo(null);
    }

    private void chargeTableData(){
        dataTable.setModel(new DefaultTableModel(modelo.getTablaFrecuencias(), modelo.getColumnNames()));
    }
}