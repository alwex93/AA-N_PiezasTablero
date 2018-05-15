package Vista;

import Controlador.Controler;
import Controlador.InterfazControler;
import Modelo.InterfazModelo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.util.Calendar;

public class ResultPage extends JFrame{
    private JLabel tiempoEjecucion;
    private JLabel proporcion;
    private JTable dataTable;
    private JPanel panel;
    private InterfazModelo modelo;
    private InterfazControler controler;
    Calendar tarda = Calendar.getInstance();

    public ResultPage(InterfazModelo modelo, File fichero){
        super("Resultado Compresión");
        this.modelo = modelo;
        controler = new Controler(this.modelo);
        init(fichero);
    }

    private void init(File fichero){
        Reloj reloj = new Reloj();
        setSize(640,480);
        long initTime;
        if(modelo != null){
            initTime = System.currentTimeMillis();
            controler.comprimirFichero(fichero);
            reloj.Detener();
            chargeTableData();
            tiempoEjecucion.setText(reloj.getTiempo() + "." + String.valueOf(System.currentTimeMillis() - initTime));
            proporcion.setText(controler.getFileLenght());
        }
        add(panel);
        pack();
        setLocationRelativeTo(null);
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

        /*TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
        sorter.setComparator(0, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                String st1 = (String)o1;
                String st2 = (String)o2;
                byte b1 = Byte.parseByte(st1.substring(0, st1.indexOf("(") - 1));
                byte b2 = Byte.parseByte(st2.substring(0, st2.indexOf("(") - 1));
                return Byte.compare(b1, b2);
            }
            @Override
            public boolean equals(Object o2) {
                return this.equals(o2);
            }
        });
        sorter.setComparator(1, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Integer int1 = (Integer)o1;
                Integer int2 = (Integer)o2;
                return int1.compareTo(int2);
            }
            @Override
            public boolean equals(Object o2) {
                return this.equals(o2);
            }
        });
        sorter.setComparator(2, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                String st1 = (String)o1;
                String st2 = (String)o2;
                return Integer.compare(st1.length(), st2.length());
            }
            @Override
            public boolean equals(Object o2) {
                return this.equals(o2);
            }
        });
        dataTable.setRowSorter(sorter);*/
        dataTable.setAutoCreateRowSorter(true);
        dataTable.setModel(model);
    }
}