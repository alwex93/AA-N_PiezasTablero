package Vista;

import Controlador.ControlerInterface;

public class Marcador implements Runnable{

    private VentanaPrincipal padre;
    private ControlerInterface controler;

    private final long SLEEP = 3000; //3 segundos

    public Marcador(VentanaPrincipal padre, ControlerInterface controler){
        this.padre = padre;
        this.controler = controler;
    }

    @Override
    public void run() {
        do{
            try {
                checkTexto();
                Thread.sleep(SLEEP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while(true);
    }

    private void checkTexto(){
        String texto = padre.getTexto();
        int lastSepPos = texto.lastIndexOf(' ');
        if (lastSepPos != -1 && lastSepPos < texto.length()){
            padre.marcarPalabras(controler.comprobar(texto.substring(0, texto.lastIndexOf(' '))));
        }
    }
}
