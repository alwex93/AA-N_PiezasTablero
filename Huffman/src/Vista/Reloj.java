package Vista;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author alejandro
 */
public class Reloj {
    private int segundos = 0, minutos = 0, horas = 0;
    private Timer timer = new Timer();

    class Contador extends TimerTask {

        @Override
        public void run() {
            segundos++;

            System.out.println("segundo: " + segundos);
        }
    }

    public void Contar() {
        this.segundos = 0;
        timer = new Timer();
        timer.schedule(new Contador(), 0, 1000);
    }

    //Detiene el contador
    public void Detener() {
        timer.cancel();
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
        return horas + ":" + minutos + ":" + segundos;
    }
}
