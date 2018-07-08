package Modelo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Bandera {

    private BufferedImage content;
    private String nombreBandera;
    private Pixel[] pixels;

    private double numBlack, numWhite;
    private double numRed, numGreen, numBlue;
    private double numMagenta, numYellow, numCyan;

    public Bandera(String path){
        numRed = numGreen = numBlue = numMagenta = numYellow = numCyan = numBlack = numWhite = 0;
        if (path.toLowerCase().contains(".jpg")){
            content = readJPG(path);
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

    private BufferedImage readJPG(String path){
        BufferedImage img;
        try {
            img = ImageIO.read(new File(path));
            int[] colores = img.getRGB(0, 0, img.getWidth(), img.getHeight(), null, 0, img.getWidth());
            pixels = new Pixel[colores.length];
            for(int pos = 0; pos < colores.length; pos++){
                pixels[pos] = new Pixel(colores[pos]);
            }
            return img;
        } catch (IOException e){
            return null;
        }
    }
    //Opcional, si hay tiempo
    /*private BufferedImage readPNG(String path){
        BufferedImage img;
        try {
            img = ImageIO.read(new File(path));
            DataBuffer bf = img.getRaster().getDataBuffer();
            System.out.println(bf.getSize());
            pixels = new Pixel[bf.getSize()/4 - 1];
            int dataBuffer = 0;
            for (int pixel = 0; pixel < bf.getSize()/4; pixel++){
                if (dataBuffer + 4 < bf.getSize()){
                    int p1 = bf.getElem(dataBuffer++);
                    int p2 = bf.getElem(dataBuffer++);
                    int p3 = bf.getElem(dataBuffer++);
                    int p4 = bf.getElem(dataBuffer++);
                    System.out.println("Pixel[" + pixel + "]: " + p1 + "," + p2 + "," + p3 + "," + p4);
                    //pixels[pixel] = new Pixel(p2, p3, p4);
                } else {
                    break; //Si no hay mas colores en el buffer --> acaba
                }
            }
            return img;
        } catch (IOException e){
            return null;
        }
    }*/

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
