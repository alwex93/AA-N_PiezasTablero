package Vista;

import java.util.Objects;

public class Punto {

    private int x,y;

    public Punto(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Punto punto = (Punto) o;
        return x == punto.x &&
                y == punto.y;
    }

    @Override
    public int hashCode() {

        return Objects.hash(x, y);
    }
}
