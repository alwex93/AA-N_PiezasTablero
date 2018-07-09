package Modelo;

import javax.swing.*;
import java.io.File;

public class Datos implements ModelInterface{

    private Bandera[] banderas;
    private Bandera imagenBlanca;
    private GammaColores paleta;

    private final double CONFIANZA = 0.5;

    public Datos(String pathCarpeta){
        paleta = new GammaColores();
        cargarBanderas(pathCarpeta);

    }

    private void cargarBanderas(String pathCarpeta){
        File[] ficheros = new File(pathCarpeta).listFiles();
        if (ficheros != null){
            banderas = new Bandera[ficheros.length];
            for (int bandera = 0; bandera < ficheros.length; bandera++){
                banderas[bandera] = new Bandera(ficheros[bandera].getPath(), 100);
                banderas[bandera].definirColoresBasicos(paleta);
            }
        }
    }

    @Override
    public Bandera cargarBanderaAdivinar(int bandera, int porcentajePixeles){
        Bandera adivinar = new Bandera(banderas[bandera].getPath(), porcentajePixeles);
        adivinar.definirColoresBasicos(paleta);
        int masParecida = checkBandera(adivinar);
        if (masParecida < banderas.length){
            return banderas[masParecida];
        } else {
            return imagenBlanca;
        }
    }

    @Override
    public void CambiarCarpeta(String rutaCarpeta) {
        cargarBanderas(rutaCarpeta);
    }

    @Override
    public void setImagenBlanca(String path) {
        imagenBlanca = new Bandera(path, 100);
    }

    private int checkBandera(Bandera banderaComprobar) {
        int bandera;
        for (bandera = 0; bandera < banderas.length && !tienenMismoColor(banderas[bandera], banderaComprobar); bandera++);
        return bandera;
    }

    private boolean tienenMismoColor(Bandera comprobar, Bandera bandera){
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
    public String getNameBandera(int bandera) {
        return banderas[bandera].getNombreBandera();
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
