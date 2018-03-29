/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Piezas;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
/**
 *
 * @author alejandro
 */
public class Tablero2 extends JPanel {

    private boolean solucion = false, punto = true;
    private int[][] tablero;
    private Piezas[] colocar;

    public Tablero2(int n) {
        
        tablero = new int[n][n];
        for (int[] tablero1 : tablero) {
            for (int j = 0; j < tablero.length; j++) {
                tablero1[j] = 0;
                
            }
        }

    }

    public int InicializarPosPosibles(ArrayList PosPosibles, int n) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                if (tablero[i][j] == 0) {
                    int[] coordenada = new int[2];
                    coordenada[0] = j;
                    coordenada[1] = i;
                    PosPosibles.add(coordenada);
                }
            }
        }
        return PosPosibles.size() - 1;
    }

    public int[][] getTablero() {
        return tablero;
    }

    public boolean Solucion(int n) throws InterruptedException {
        
        ArrayList PosPosibles = new ArrayList();
        int numPos = InicializarPosPosibles(PosPosibles, n);
        solucion = false;
        while (n < colocar.length && numPos >= 0 && !solucion) {
            Piezas pieza = colocar[n];
            int x = 0, y = 0;
            while (punto && numPos >= 0) {
                int[] coordenadas = (int[]) PosPosibles.get(numPos);
                x = coordenadas[0];
                y = coordenadas[1];
                tablero = Colocar(pieza, tablero, x, y);
                numPos--;
            }
            punto = true;
            Print(tablero);
//            Thread.sleep(1000);
            this.repaint();
            if (n < colocar.length && pieza.esPosible()) {
                solucion = Solucion(n + 1);
                if (!solucion) {
                    tablero = pieza.Borrar(tablero, x, y);
                        
                    Print(tablero);
//                    Thread.sleep(1000);
                    this.repaint();
                    if (numPos > 0) {
                        PosPosibles.remove(numPos);
                        numPos--;
                    }

                }
            }
        }
        if (n < colocar.length) {

            boolean ret = true;
            for (int aux = colocar.length - 1; aux > 0; aux--) {
                ret = ret && colocar[aux].esPosible();
            }
            return ret;
        } else {
            return true;
        }

    }

    public int[][] Colocar(Piezas pieza, int[][] tablero, int x, int y) {
        int[][] aux = new int[tablero.length][tablero.length];
        for (int i = 0; i < tablero.length; i++) {
            System.arraycopy(tablero[i], 0, aux[i], 0, tablero.length);
        }

        tablero = pieza.Movimientos(tablero, x, y);
        if (pieza.esPosible()) {
            punto = false;
            return tablero;
        } else {
            punto = true;
            return aux;
        }
    }

    public boolean haySolucion() {
        return solucion;
    }

    public void ObtenerPiezas(int dama, int rey, int alfil, int torre, int caballo, int lancero, int halcon) {
        int piezas = dama + rey + alfil + caballo + torre + lancero + halcon;
        Piezas pieza = null;
        colocar = new Piezas[piezas];
        int n = 0;
        while (piezas > 0) {
            if (dama != 0) {
                pieza = new Dama();
                dama--;
            } else if (rey != 0) {
                pieza = new Rey();
                rey--;
            } else if (alfil != 0) {
                pieza = new Alfil();
                alfil--;
            } else if (torre != 0) {
                pieza = new Torre();
                torre--;
            } else if (caballo != 0) {
                pieza = new Caballo();
                caballo--;
            } else if (lancero != 0) {
                pieza = new Halcon();
                lancero--;
            } else if (halcon != 0) {
                pieza = new Lancero();
                halcon--;
            }
            colocar[n] = pieza;
            n++;
            piezas = dama + rey + alfil + caballo + torre + lancero + halcon;
        }
    }

    private void Print(int[][] tablero) {
        System.out.println("Tablero: \n");
        for (int[] pro1 : tablero) {
            for (int y = 0; y < tablero.length; y++) {
                System.out.print( (char)pro1[y] + " ");
            }
            System.out.println(" ");
        }
        System.out.println("\n\n");
    }

    @Override
    public void paint (Graphics g){
        pintarTablero(g);
        pintarPiezas(g);
        g.dispose();
    }
    private void pintarTablero (Graphics g){
        for (int i = 0; i < tablero.length; i++) {           
            for (int j = 0;j< tablero.length ; j++) {
                if((i+j)%2 == 0){
                    g.setColor(Color.WHITE);
                }else{
                    g.setColor(Color.BLACK);
                }
                g.drawRect(j*50, i*50, 50, 50);
                g.fillRect(j*50, i*50, 50, 50);
            }
        }
    }
    private void pintarPiezas(Graphics g){
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                char caracter = (char) tablero[i][j];
                Piezas pieza;
                switch (caracter){
                    case 'D': 
                        pieza = new Dama();
                        g.drawImage(pieza.getImagen().getImage(), i*50,j*50 , this);
                        break;
                    case 'R':
                        pieza = new Rey();
                        g.drawImage(pieza.getImagen().getImage(), i*50,j*50 , this);
                        break;
                    case 'A':
                        pieza = new Alfil();
                        g.drawImage(pieza.getImagen().getImage(), i*50,j*50 , this);
                        break;
                    case 'T':
                        pieza = new Torre();
                        g.drawImage(pieza.getImagen().getImage(), i*50,j*50 , this);
                        break;
                    case 'C':
                        pieza = new Caballo();
                        g.drawImage(pieza.getImagen().getImage(), i*50,j*50 , this);
                        break;
                    case 'L':
                        pieza = new Lancero();
                        g.drawImage(pieza.getImagen().getImage(), i*50,j*50 , this);
                        break;
                    case 'H':
                        pieza = new Halcon();
                        g.drawImage(pieza.getImagen().getImage(), i*50,j*50 , this);
                        break;
                }
            }
        }

    }
}
