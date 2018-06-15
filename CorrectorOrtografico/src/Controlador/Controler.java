package Controlador;

import Vista.Palabra;

import java.io.*;
import java.util.ArrayList;

public class Controler implements ControlerInterface{

    private int[][] dist;
    private String[] diccionario;

    private final int MAXSUSTITUTOS = 10;

    public Controler(){
        File file = new File("recursos/ES.dic");
        ArrayList<String> dicContainer = new ArrayList<>();
        try {
            BufferedReader bf = new BufferedReader(new FileReader(file));
            while (bf.ready()){
                dicContainer.add(bf.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        diccionario = new String[dicContainer.size()];
        diccionario = dicContainer.toArray(diccionario);
    }

    @Override
    public Palabra[] comprobar(String texto) {
        String[] palabras = texto.replaceAll(",|.", "").split(" ");
        return new Palabra[0];
    }

    private Palabra[] getPalabras(String texto){
        ArrayList<Palabra> palabras = new ArrayList<>();
        for(int letra = 0; letra < texto.length(); letra++){
            int initPal = letra;
            do {
                letra++;
            } while (texto.charAt(letra) != ' ');
            int endPal = letra - 1;
            String palabra = texto.substring(initPal, endPal);
            palabras.add(new Palabra(palabra, initPal, endPal, getSustitutos(palabra)));
        }
        Palabra[] ret = new Palabra[palabras.size()];
        return palabras.toArray(ret);
    }

    private String[] getSustitutos(String palabra){
        String[] sustitutos = new String[MAXSUSTITUTOS];
        for(int sust = 0; sust < MAXSUSTITUTOS; sust++){
            
        }
    }

    private int distancia(char[] pal1, char[] pal2){
        int coste, d[][] = new int[pal1.length + 1][pal2.length + 1];

        for (int i = 0; i < pal1.length + 1; i++) {
            for (int j = 0; j < pal2.length + 1; j++) {
                d[i][j] = -1;
            }
        }

        for (int i = 0; i < pal1.length + 1; i++) {
            d[i][0] = i;
        }

        for (int j = 0; j < pal2.length + 1; j++) {
            d[0][j] = j;
        }
        printDist(d);
        for (int let1 = 0; let1 < pal1.length ; let1++) {
            for (int let2 = 0; let2 < pal2.length; let2++) {
                if (pal1[let1] == pal2[let2]) {
                    coste = 0;
                } else {
                    coste = 1;
                }
                int delete = d[let1][let2 + 1] + 1;
                int insert = d[let1 + 1][let2] + 1;
                int replace = d[let1][let2] + coste;

                d[let1 + 1][let2 + 1] = Math.min(Math.min(delete,    //delete  d[a-1][b] (+1)= d[a][b + 1]
                        insert),                                     //insert  d[a][b-1] (+1)= d[a+1][b]
                        replace);                                     //replace d[a-1][b-1] (+1)= d[a][b]
                printDist(d);
            }

        }
        dist = d;
        return d[pal1.length][pal2.length];
    }

    public void testDistancia(String pal1, String pal2){
        System.out.println(distancia(pal1.toCharArray(), pal2.toCharArray()));
        printDist(dist);
    }

    private void printDist(int[][] dist){
        for (int[] fila: dist) {
            for (int columna : fila) {
                System.out.print(columna + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
