package Vista;

import Controlador.ControlerInterface;

public class Marcador implements Runnable{

    private VentanaPrincipal padre;

    private final long SLEEP = 3000; //3 segundos

    public Marcador(VentanaPrincipal padre){
        this.padre = padre;
    }

    @Override
    public void run() {
        do{
            try {
                padre.checkTexto();
                Thread.sleep(SLEEP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while(true);
    }
}
