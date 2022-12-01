package sample;

/**
 * Classe che definisce un punto
 * @author Ismaele Carbonari
 */
public class Point {
    private final double x;
    private final double y;

    public Point() {
        this.x = 0;
        this.y = 0;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
