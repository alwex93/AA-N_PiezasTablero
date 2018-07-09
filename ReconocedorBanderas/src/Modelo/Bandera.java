package Modelo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class Bandera {

    private BufferedImage content;
    private String nombreBandera;
    private Pixel[] pixels;
    private String path;

    private double numBlack, numWhite;
    private double numRed, numGreen, numBlue;
    private double numMagenta, numYellow, numCyan;

    public Bandera(String path, int numPixeles){
        this.path = path;
        numRed = numGreen = numBlue = numMagenta = numYellow = numCyan = numBlack = numWhite = 0;
        if (path.toLowerCase().contains(".jpg")){
            initBandera(path, numPixeles);
            path = path.replace(".jpg", "");
        } else if (path.toLowerCase().contains(".png")){
            //content = readPNG(path);
            path = path.replace(".png", "");
        }
        nombreBandera = limpiarNombre(path);
    }

    private String limpiarNombre(String path){
        while (path.contains("\\")){
            path = path.substring(path.indexOf("\\") + 1);
        }
        return path.replace("_", " ");
    }

    private void initBandera(String path, int porcentajePixeles){
        content = readJPG(path);
        if (content != null) {
            pixels = getPixeles(porcentajePixeles,
                    content.getRGB(0, 0, content.getWidth(), content.getHeight(), null, 0, content.getWidth()));
        }
    }

    private BufferedImage readJPG(String path){
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e){
            return null;
        }
    }

    private Pixel[] getPixeles(int porcentajePixeles, int[] pixeles){
        Pixel[] ret;
        int numPixeles;
        if(porcentajePixeles == 0 || porcentajePixeles == 100){
            ret = new Pixel[pixeles.length];
            for(int pos = 0; pos < pixeles.length; pos++){
                ret[pos] = new Pixel(pixeles[pos]);
            }
            return ret;
        } else {
            numPixeles = new BigDecimal(pixeles.length*((double)porcentajePixeles/100)).intValue();
            ret = new Pixel[numPixeles];
            pixeles = randomPixeles(numPixeles, pixeles);

            for(int pos = 0; pos < numPixeles; pos++){
                ret[pos] = new Pixel(pixeles[pos]);
            }

            return ret;
        }
    }

    public int[] randomPixeles(int numPixeles, int[] pixeles){
        Random rnd = new Random();
        Set<Integer> posPixeles = new LinkedHashSet<>();
        while(posPixeles.size() < numPixeles){
            posPixeles.add(Math.abs(rnd.nextInt()% pixeles.length));
        }

        int[] seleccionPixeles = new int[numPixeles];
        int selPos = 0;
        for(int pos : posPixeles){
            seleccionPixeles[selPos] = pixeles[pos];
            selPos++;
        }
        return seleccionPixeles;
    }

    public Icon getImagen(){
        return new ImageIcon(content);
    }

    public void definirColoresBasicos(GammaColores paleta){
        double totalPixeles = (double) pixels.length;
        for (Pixel pixel : pixels) {
            contarColores(pixel.getColorBasico(paleta));
        }
        numBlack = getPorcentaje(numBlack/totalPixeles);
        numWhite = getPorcentaje(numWhite/totalPixeles);


        numRed = getPorcentaje(numRed/totalPixeles);
        numGreen = getPorcentaje(numGreen/totalPixeles);
        numBlue = getPorcentaje(numBlue/totalPixeles);

        numMagenta = getPorcentaje(numMagenta/totalPixeles);
        numYellow = getPorcentaje(numYellow/totalPixeles);
        numCyan = getPorcentaje(numCyan/totalPixeles);
    }

    private double getPorcentaje(double num){
        return new BigDecimal(num*100).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public double getNumBlack() {
        return numBlack;
    }

    public double getNumWhite() {
        return numWhite;
    }

    public double getNumRed() {
        return numRed;
    }

    public double getNumGreen() {
        return numGreen;
    }

    public double getNumBlue() {
        return numBlue;
    }

    public double getNumMagenta() {
        return numMagenta;
    }

    public double getNumYellow() {
        return numYellow;
    }

    public double getNumCyan() {
        return numCyan;
    }

    public String getNombreBandera() {
        return nombreBandera;
    }

    public String getPath(){
        return path;
    }

    private void contarColores(Color color){
        if (color == Color.BLACK){
            numBlack++;
        } else if (color == Color.WHITE){
            numWhite++;
        } else if (color == Color.RED){
            numRed++;
        } else if (color == Color.GREEN){
            numGreen++;
        } else if (color == Color.BLUE){
            numBlue++;
        } else if (color == Color.MAGENTA){
            numMagenta++;
        } else if (color == Color.YELLOW){
            numYellow++;
        } else if (color == Color.CYAN){
            numCyan++;
        }
    }
}
