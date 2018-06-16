package Controlador;

import Vista.Palabra;
import java.util.ArrayList;

public class Controler implements ControlerInterface{

    private int[][] dist;
    private Diccionario diccionario;


    public Controler(){
        diccionario = new Diccionario();
    }

    @Override
    public Palabra[] comprobar(String texto) {
        //String[] palabras = texto.replaceAll(",|.", "").split(" ");
        return getPalabras(texto.toLowerCase());
    }

    private Palabra[] getPalabras(String texto){
        ArrayList<Palabra> palabras = new ArrayList<>();
        Palabra pal;
        for(int letra = 0; letra < texto.length(); letra++){
            int initPal = letra;
            do {
                letra++;
            } while (letra < texto.length() && texto.charAt(letra) != ' ');
            String palabra = texto.substring(initPal, letra);
            pal = new Palabra(palabra, initPal, letra, diccionario.getSustitutos(palabra));
            if (!pal.isCorregida()){
                palabras.add(pal);
            }
        }
        Palabra[] ret = new Palabra[palabras.size()];
        return palabras.toArray(ret);
    }
}
