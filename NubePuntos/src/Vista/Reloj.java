/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author alejandro
 */
public class Reloj {
    private int segundos = 0, minutos = 0, horas = 0;
    private Timer timer = new Timer();
    private Calendar tarda = Calendar.getInstance();
    private long tardo;

    class Contador extends TimerTask {

        @Override
        public void run() {
            segundos++;

            //System.out.println("segundo: " + segundos);
        }
    }

    public void Contar() {
        tardo = tarda.getTimeInMillis();
        this.segundos = 0;
        timer = new Timer();
        timer.schedule(new Contador(), 0, 1000);
    }

    //Detiene el contador
    public void Detener() {
        timer.cancel();
        tardo = tarda.getTimeInMillis() - tardo;
    }

    //Metodo que retorna los segundos transcurridos
    public String getTiempo() {
        while (segundos >= 60){
            minutos++;
            segundos -= 60;
        }
        while (minutos >= 60){
            horas++;
            minutos -= 60;
        }
        return horas + ":" + minutos + ":" + segundos + "." + String.valueOf(tardo);
    }
}
