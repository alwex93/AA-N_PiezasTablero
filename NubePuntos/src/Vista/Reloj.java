/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author alejandro
 */
public class Reloj {
    private long nano;

    private final int HORAS = 12960000;
    private final int MINUTOS = 216000;
    private final int SEGUNDOS = 3600;
    private final int MILISEGUNDOS = 60;

    public void Contar() {
        nano = System.nanoTime();
    }

    //Detiene el contador
    public void Detener() {
        nano = new BigDecimal((System.nanoTime() - nano) /1000).setScale(4, RoundingMode.HALF_UP).longValue();
    }

    //Metodo que retorna los segundos transcurridos
    public String getTiempo() {
        return conversor(HORAS) + ":" + conversor(MINUTOS) + ":" + conversor(SEGUNDOS) + "." + conversor(MILISEGUNDOS) + "." + nano;
    }

    private int conversor(int conv){
        int superior = 0;
        while (nano >= conv){
            superior++;
            nano -= conv;
        }
        return superior;
    }
}
