package Modelo;

public abstract class Pieza {

    private char value;
    private String name;
    private String img;


    public Pieza(String name, char value, String relativePath){
        this.name = name;
        this.value = value;
        img = relativePath;
    }

    public int getValue(){
        return Character.getNumericValue(value);
    }
}
