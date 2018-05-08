package Vista;

import Controlador.Controler;
import Controlador.InterfazControler;
import Modelo.Datos;
import Modelo.InterfazModelo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ResultPage extends JFrame{
    private JLabel tiempoEjecucion;
    private JLabel proporcion;
    private JTable dataTable;
    private JPanel panel;
    private InterfazModelo modelo;
    private InterfazControler controler;

    public ResultPage(InterfazModelo modelo){
        super("Resultado Compresión");
        this.modelo = modelo;
        controler = new Controler(this.modelo);
        init();
    }

    private void init(){
        setSize(640,480);
        if(modelo != null){
            controler.generarArbolHuffman();
            chargeTableData();
        }
        add(panel);
        pack();
        setLocationRelativeTo(null);
    }

    private void chargeTableData(){
        dataTable.setModel(new DefaultTableModel(modelo.getTablaFrecuencias(), modelo.getColumnNames()){
            public boolean isCellEditable(int row, int column) {
                return false;//This causes all cells to be not editable
            }
        });
    }
}