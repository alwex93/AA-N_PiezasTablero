package Modelo;

import java.awt.*;

public class GammaColores {

    private final int LINDE_BLANCO = 220;
    private final int LINDE_NEGRO = 50;

    public Color getPrimariColor(int color){

        int[] rgb = getPrimaryColor((color & 0x00ff0000) >> 16,
                                    (color & 0x0000ff00) >> 8, color & 0x000000ff);
        int red = rgb[0];
        int green = rgb[1];
        int blue = rgb[2];

        if (red == green && green == blue && blue == 1){
            return Color.WHITE;
        } else if (red == green && green == 1){
            return Color.YELLOW;
        } else if (red == blue && blue == 1){
            return Color.MAGENTA;
        } else if (green == blue && blue == 1){
            return Color.CYAN;
        } else if (red == 1){
            return Color.RED;
        } else if (green == 1){
            return Color.GREEN;
        } else if (blue == 1){
            return Color.BLUE;
        } else if (red == green && green == blue && blue == 0){
            return Color.BLACK;
        } else {
            return Color.GRAY;
        }
    }

    private int[] getPrimaryColor(int red, int green, int blue){
        int[] color = {0, 0, 0}; //mi rgb
        if (isInsideLinde(red)){
            color[0] = 1;
        }
        if (isInsideLinde(green)){
            color[1] = 1;
        }
        if (isInsideLinde(blue)){
            color[2] = 1;
        }
        return color;
    }

    private boolean isInsideLinde(int color){
        return color > LINDE_NEGRO && color < LINDE_BLANCO;
    }
}
