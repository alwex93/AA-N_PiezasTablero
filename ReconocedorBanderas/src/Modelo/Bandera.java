package Modelo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bandera {

    BufferedImage content;

    public Bandera(String path){
        try {
            content = ImageIO.read(new File(path));
        } catch (IOException e){
            content = null;
        }
    }

    public Icon GetImagen(){
        return new ImageIcon(content);
    }


}
