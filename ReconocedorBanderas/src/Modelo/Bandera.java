package Modelo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;

public class Bandera {

    private BufferedImage content;
    private Pixel[] pixels;

    public Bandera(String path){
        try {
            content = ImageIO.read(new File(path));
            DataBuffer bf = content.getRaster().getDataBuffer();
            System.out.println("Size: " + bf.getSize());
            String sep = "";
            for (int fila = 0; fila < bf.getSize(); fila++){
                System.out.print(sep + bf.getElem(fila));
                sep = ",";
            }
        } catch (IOException e){
            content = null;
        }

    }

    public Icon GetImagen(){
        return new ImageIcon(content);
    }


}
