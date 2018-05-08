package Controlador;

import Modelo.Datos;

import java.io.*;
import org.apache.commons.io.IOUtils;

public class Controler {

    private Datos modelo;

    public Controler(Datos modelo){
        this.modelo = modelo;
    }

    public void readFile(File fichero){
        FileReader reader;
        byte[] bytes;
        try{
            reader = new FileReader(fichero);
            bytes = IOUtils.toByteArray(reader);
            for (byte b : bytes) {
                modelo.addSimbolo(b);
            }
            reader.close();
        }catch (IOException e){
            System.out.println("error");
        }
        modelo.showFrecuencias();
    }
}
