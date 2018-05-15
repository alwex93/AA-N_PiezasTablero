package Modelo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class Datos {
    private Punto[] puntos;
    private int cercano1, cercano2;
    private boolean hayCercanos;

    public Datos(int numPuntos, int maxX, int maxY){
        puntos = new Punto[numPuntos];
        generarPuntos(numPuntos, maxX, maxY);
        hayCercanos = false;
    }

    public Datos(Punto[] puntos){
        this.puntos = puntos;
    }

    public void exchangeNumbers(int i, int j) {
        Punto temp = puntos[i];
        puntos[i] = puntos[j];
        puntos[j] = temp;
    }

    public boolean isEquals(int elem1, int elem2){
        return puntos[elem1].equals(puntos[elem2]);
    }

    public int getCoordenadaY(int punto){
        return puntos[punto].getY();
    }

    public int getCoordenadaX(int punto){
        return puntos[punto].getX();
    }

    public int getLenght(){
        return puntos.length;
    }

    public void setCercanos(int cercano1, int cercano2){
        this.cercano1 = cercano1;
        this.cercano2 = cercano2;
        hayCercanos = true;
    }

    public void printInfoPuntos(){
        System.out.println("\n\nPuntos:");
        int pn = 0;
        for (Punto p : puntos) {
            System.out.println(pn + p.toString());
            pn++;
        }
        if(hayCercanos()){
            System.out.println("Cercanos:\n" + puntos[cercano1].toString() + "\n"
                    + puntos[cercano2].toString() + "\n" + distancia(cercano1, cercano2));
        }
    }

    public long distancia(int p1, int p2){
        long distX = Math.abs(puntos[p1].getX() - puntos[p2].getX());
        long distY = Math.abs(puntos[p1].getY() - puntos[p2].getY());
        return new BigDecimal(distX  + distY).setScale(2, RoundingMode.HALF_UP).longValue();
    }

    public long getDistanciaMinima(){
        if (hayCercanos){
            return distancia(cercano1, cercano2);
        } else {
            return Long.MAX_VALUE;
        }
    }

    public boolean hayCercanos(){
        return hayCercanos;
    }

    public int getPrimerCercanoX(){
        return puntos[cercano1].getX();
    }

    public int getPrimerCercanoY(){
        return puntos[cercano1].getY();
    }

    public int getSegundoCercanoX(){
        return puntos[cercano2].getX();
    }

    public int getSegundoCercanoY(){
        return puntos[cercano2].getY();
    }

    public void generarPuntos(int n, int maxX, int maxY){
        puntos = new Punto[n];
        Random rnd = new Random(1993);
        for (int punto = 0; punto < n; punto++){
            puntos[punto] = new Punto(Math.abs(rnd.nextInt()) % maxX, Math.abs(rnd.nextInt()) % maxY);
        }
        hayCercanos = false;
    }
}
