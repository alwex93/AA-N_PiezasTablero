package Controlador;

import com.sun.deploy.util.ArrayUtil;
import org.apache.commons.lang.ArrayUtils;
import sun.text.normalizer.UTF16;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class Diccionario {


    private String[] diccionario;
    private final int MAXSUSTITUTOS = 5;
    private final int MAXDISTANCIA = 5;

    public Diccionario(){
        File file = new File("recursos/ES.dic");
        ArrayList<String> dicContainer = new ArrayList<>();
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file), "UTF-8"));
            while (bf.ready()){
                dicContainer.add(bf.readLine());
                if(dicContainer.size() > 87790){
                    System.out.println(dicContainer.get(dicContainer.size() - 1));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        diccionario = new String[dicContainer.size()];
        diccionario = dicContainer.toArray(diccionario);
    }

    public String[] getSustitutos(String palabra){
        Integer[] distancias = new Integer[diccionario.length];
        for(int d = 0; d < diccionario.length; d++){
            distancias[d] = distancia(diccionario[d].toCharArray(), palabra.toCharArray());
        }
        String[] sustitutos = new String[MAXSUSTITUTOS];

        if (ArrayUtils.contains(distancias, 0)){
            return null;
        }

        for (int dicSust = 0, distancia = 1, pSust = 0; dicSust < distancias.length; dicSust++){
            if (distancias[dicSust] == distancia){
                sustitutos[pSust] = diccionario[dicSust];
                pSust++;
            }
            if (pSust == MAXSUSTITUTOS || distancia == MAXDISTANCIA){
                return sustitutos;
            }else if (dicSust == distancias.length - 1 && pSust < MAXSUSTITUTOS){
                dicSust = 0;
                distancia++;
            }
        }
        return sustitutos;
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
            }
        }
        return d[pal1.length][pal2.length];
    }
}
