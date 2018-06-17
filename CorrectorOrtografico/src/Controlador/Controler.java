package Controlador;

import Vista.Palabra;
import java.util.ArrayList;

public class Controler implements ControlerInterface{

    private int[][] dist;
    private Diccionario diccionario;

    private final char[] EX_CARACTER = {'.', ',', '!', '?'};


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
            int endPal = getPalabra(texto, letra);
            String palabra = texto.substring(initPal, endPal);
            pal = new Palabra(palabra, initPal, endPal, diccionario.getSustitutos(palabra));
            if (!pal.isCorregida()){
                palabras.add(pal);
            }
        }
        Palabra[] ret = new Palabra[palabras.size()];
        return palabras.toArray(ret);
    }

    private int getPalabra(String texto, int endPal){
        int end = endPal - 1;
        char lastLeter = texto.charAt(end);
        for (char ex: EX_CARACTER) {
            if (lastLeter == ex){
                return endPal - 1;
            }
        }
        return endPal;
    }
}
