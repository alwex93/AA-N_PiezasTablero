package Controlador;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import Controlador.HeapNodos.HeapNodos;
import Controlador.HeapNodos.Nodo;
import Modelo.InterfazModelo;
import org.apache.commons.io.IOUtils;

public class Controler implements InterfazControler{

    private InterfazModelo modelo;
    private HeapNodos lista;
    private int escala;
    private byte[] compilado;
    private String namePath;

    public Controler(InterfazModelo modelo){
        this.modelo = modelo;
    }

    private double fileLenght(double lenght){
        escala = 0;
        while(lenght > 1024){
            lenght /= 1024;
            escala++;
        }
        return lenght;
    }

    @Override
    public String getFileLenghtAntes() {
        return getFileLenght(modelo.getFileLenght());
    }

    @Override
    public String getFileLenghtDespues() {
        return getFileLenght(compilado.length);
    }

    @Override
    public boolean generarFichero() {
        FileOutputStream out = null;
        String path = namePath + ".comp";
        File check = new File (path);
        if (check.exists()){
            return true;
        }
        try {
            out = new FileOutputStream(path);
            out.write(compilado);
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String getFileLenght(double lenghtFile){
        BigDecimal tm = new BigDecimal(fileLenght(lenghtFile));
        StringBuilder lenght = new StringBuilder().append(tm.setScale(2, RoundingMode.HALF_UP));
        switch (escala){
            case 0:
                lenght.append(" B");
                break;
            case 1:
                lenght.append(" KB");
                break;
            case 2:
                lenght.append(" MB");
                break;
            case 3:
                lenght.append(" GB");
                break;
        }
        return lenght.toString();
    }

    private void recorrerLista(Nodo nodo, String value){
        if(!nodo.isInterno()){
            modelo.setHaffmanValue(nodo.getId(), value);
        } else {
            if (nodo.getCero() != null){
                recorrerLista(nodo.getCero(), value + "0");
            }
            if (nodo.getUno() != null){
                recorrerLista(nodo.getUno(), value + "1");
            }
        }
    }

    public double comprimirFichero(File fichero){
        byte[] contenido = readFile(fichero);
        generarArbolHuffman();
        setHaffmanValues();
        StringBuilder bruto = new StringBuilder();
        for (byte b: contenido) {
            bruto.append(modelo.getHaffmanValue(b));
        }
        while (bruto.length() % 8 != 0){
            bruto.append("0");
        }
        System.out.println(bruto.length());
        compilado = new BigInteger(bruto.toString(), 2).toByteArray();
        return ((double)compilado.length/contenido.length)*100;
    }

    private byte[] readFile(File fichero){
        FileReader reader;
        byte[] bytes;
        try{
            namePath = fichero.getAbsolutePath();
            reader = new FileReader(fichero);
            bytes = IOUtils.toByteArray(reader, "UTF8");
            modelo.setFileLenght(fichero.length());
            for (byte b : bytes) {
                modelo.addSimbolo(b);
            }
            reader.close();
            return bytes;
        }catch (IOException e){
            System.out.println("error");
        }
        return null;
    }

    private void generarArbolHuffman(){
        int sizeTable = modelo.sizeTable();
        //generamos la lista ordenada de menor a mayor
        lista = new HeapNodos(sizeTable);
        for (int it = 0; it < sizeTable; it++){
            lista.addNodo(new Nodo(modelo.getValue(it), modelo.getFrecuencia(it)));
        }
        do{
            Nodo[] primeros = lista.getPrimeros();
            if (primeros[0] != null && primeros[1] != null){
                Nodo nuevo = new Nodo(primeros[0].getCompareValue() + primeros[1].getCompareValue());
                nuevo.setCero(primeros[0]);
                nuevo.setUno(primeros[1]);
                lista.addNodo(nuevo);
            }
        } while(lista.quedanNodos());
    }

    private void setHaffmanValues(){
        recorrerLista(lista.getNodo(),"");
    }
}
