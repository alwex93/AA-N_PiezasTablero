package Modelo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Datos {

    private Bandera[] banderas;
    private GammaColores paleta;

    public Datos(){
        paleta = new GammaColores();
        File[] ficheros = new File("Banderas").listFiles();
        if (ficheros != null){
            banderas = new Bandera[ficheros.length];
            for (int bandera = 0; bandera < ficheros.length; bandera++){
                banderas[bandera] = new Bandera(ficheros[bandera].getPath());
                banderas[bandera].definirColoresBasicos(paleta);
            }
        }

    }

}
