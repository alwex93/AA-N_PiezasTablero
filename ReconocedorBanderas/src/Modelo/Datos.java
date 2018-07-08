package Modelo;

import javax.swing.*;
import java.io.File;

public class Datos implements ModelInterface{

    private Bandera[] banderas;
    private Bandera imagenBlanca;
    private GammaColores paleta;

    public Datos(String pathCarpeta){
        paleta = new GammaColores();
        File[] ficheros = new File(pathCarpeta + "\\Banderas").listFiles();
        if (ficheros != null){
            banderas = new Bandera[ficheros.length];
            for (int bandera = 0; bandera < ficheros.length; bandera++){
                banderas[bandera] = new Bandera(ficheros[bandera].getPath());
                banderas[bandera].definirColoresBasicos(paleta);
            }
        }
        imagenBlanca = new Bandera(pathCarpeta + "\\imagenVacia.jpg");
    }

    @Override
    public String[] getNameBanderas() {
        String[] nombres = new String[banderas.length];
        for(int pos = 0; pos < nombres.length; pos++){
            nombres[pos] = banderas[pos].getNombreBandera();
        }
        return nombres;
    }

    @Override
    public String getBanderaPath(int bandera) {
        return banderas[bandera].getPath();
    }

    @Override
    public Icon getBanderaImg(int bandera) {
        return banderas[bandera].getImagen();
    }

    @Override
    public Icon getImagenBlanca() {
        return imagenBlanca.getImagen();
    }
}
