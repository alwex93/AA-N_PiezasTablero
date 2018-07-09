package Modelo;

import javax.swing.*;
import java.io.File;

public class Datos implements ModelInterface{

    private Bandera[] banderas;
    private Bandera imagenBlanca;
    private GammaColores paleta;

    private final double CONFIANZA = 1.0;

    public Datos(String pathCarpeta){
        paleta = new GammaColores();
        File[] ficheros = new File(pathCarpeta).listFiles();
        if (ficheros != null){
            banderas = new Bandera[ficheros.length];
            for (int bandera = 0; bandera < ficheros.length; bandera++){
                banderas[bandera] = new Bandera(ficheros[bandera].getPath(), 0);
                banderas[bandera].definirColoresBasicos(paleta);
            }
        }

    }

    @Override
    public Icon cargarBanderaAdivinar(int bandera, int porcentajePixeles){
        Bandera adivinar = new Bandera(banderas[bandera].getPath(), porcentajePixeles);
        adivinar.definirColoresBasicos(paleta);
        if (checkBandera(adivinar) < banderas.length){
            return banderas[bandera].getImagen();
        } else {
            return imagenBlanca.getImagen();
        }
    }

    @Override
    public void CambiarCarpeta(String nombreCarpeta) {

    }

    @Override
    public void setImagenBlanca(String path) {
        imagenBlanca = new Bandera(path, 0);
    }

    private int checkBandera(Bandera banderaComprobar) {
        boolean esLaVandera;
        int bandera = 0;
        do{
            esLaVandera = comprobarColores(banderas[bandera], banderaComprobar);
            bandera++;
        } while(!esLaVandera && bandera < banderas.length);

        return bandera;
    }

    private boolean comprobarColores(Bandera comprobar, Bandera bandera){
        return tieneColor(comprobar.getNumBlack(), bandera.getNumBlack())       && //Negro
                tieneColor(comprobar.getNumWhite(), bandera.getNumWhite())      && //Blanco

                tieneColor(comprobar.getNumRed(), bandera.getNumRed())          && //Rojo
                tieneColor(comprobar.getNumGreen(), bandera.getNumGreen())      && //Verde
                tieneColor(comprobar.getNumBlue(), bandera.getNumBlue())        && //Azul

                tieneColor(comprobar.getNumMagenta(), bandera.getNumMagenta())  && //Magenta
                tieneColor(comprobar.getNumYellow(), bandera.getNumYellow())    && //Amarillo
                tieneColor(comprobar.getNumCyan(), bandera.getNumCyan());          //Cyan

    }

    private boolean tieneColor(double bandera, double checkValue){
        return Math.abs(bandera - checkValue) < CONFIANZA;
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
    public Icon getBanderaImg(int bandera) {
        return banderas[bandera].getImagen();
    }

    @Override
    public Icon getImagenBlanca() {
        return imagenBlanca.getImagen();
    }
}
