package Controlador;

import Modelo.Bandera;
import Modelo.GammaColores;
import Modelo.ModelInterface;
import Modelo.Pixel;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class ProbabilisticoMonteCarlo {

    private ModelInterface modelo;
    private final int SEED = 1993;

    public ProbabilisticoMonteCarlo(ModelInterface modelo){
        this.modelo = modelo;
    }

    public Icon adivinarImagen(int bandera, int numPixeles){


    }

    private Pixel[] getPixels(int bandera, int numPixeles){
        BufferedImage img;
        Pixel[] pixels = new Pixel[numPixeles];
        Random rnd = new Random(1993);
        try {
            img = ImageIO.read(new File(modelo.getBanderaPath(bandera)));
            int[] colores = img.getRGB(0, 0, img.getWidth(), img.getHeight(), null, 0, img.getWidth());
            for(int pos = 0; pos < numPixeles; pos++){
                pixels[pos] = new Pixel(colores[rnd.nextInt()]);
            }
            return pixels;
        } catch (IOException e){
            return new Pixel[0];
        }
    }

    private void definirColoresBasicos(Pixel[] pixels, GammaColores paleta){
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
