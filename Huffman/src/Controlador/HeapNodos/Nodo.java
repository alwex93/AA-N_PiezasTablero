package Controlador.HeapNodos;

public class Nodo {
    private byte value;
    private int frecuencia;
    private Nodo cero, uno;
    private boolean interno;
    
    public Nodo(byte value, int frecuencia) {
        this.value = value;
        this.frecuencia = frecuencia;
        cero = uno = null;
        interno = false;
    }

    public Nodo(int frecuencia) {
        this.frecuencia = frecuencia;
        cero = uno = null;
        interno = true;
    }

    public int compareTo(Nodo o) {
        return Integer.compare(getCompareValue(), o.getCompareValue());
    }

    public int getCompareValue(){
        return frecuencia;
    }

    public byte getId(){
        return value;
    }

    public void setCero(Nodo cero){
        this.cero = cero;
    }

    public void setUno(Nodo uno){
        this.uno = uno;
    }

    public Nodo getCero() {
        return cero;
    }

    public Nodo getUno() {
        return uno;
    }

    public boolean isInterno(){
        return interno;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder("Nodo{valor=").append(value)
                .append(", frecuencia=").append(frecuencia)
                .append(System.getProperty("line.separator"));
        if (cero != null){
            ret.append("0{").append(cero.toString()).append('}');
        }
        if (uno != null){
            ret.append("1{").append(uno.toString()).append('}');
        }
        return ret.toString();
    }
}


