/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Datos;
import huffman.Huffman;

/**
 *
 * @author AndresOverflow
 */
public class Proceso {
    /**
     * Puntero a la clase principal de la aplicación.
     */
    private final Huffman prog;
    /**
     * Puntero a la parte de la aplicación que contiene los Modelo del programa
     * (al modelo).
     */
    private Datos datos;


    public Proceso(Huffman p, int n) {
        prog = p;
    }

    
}
