package main.com.game.math;

public class Vector2D {
    public double x;
    public double y;

    public Vector2D(){
        set(0.0, 0.0);
    }

    public Vector2D(double x, double y) {
        set(x, y);
    }

    public Vector2D(Vector2D v) {
        this.x = v.x;
        this.y = v.y;
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void set(Vector2D v) {
        set(v.x, v.y);
    }

    public double getLength() {
        return Math.sqrt(x * x + y * y);
    }

    public void add(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public void add(Vector2D v) {
        this.x += v.x;
        this.y += v.y;
    }

    public void subtract(double x, double y) {
        this.x -= x;
        this.y -= y;
    }

    public void subtract(Vector2D v) {
        this.x -= v.x;
        this.y -= v.y;
    }

    public void multiply(double scalar) {
        x *= scalar;
        y *= scalar;
    }

    public void divide(double scalar) {
        x /= scalar;
        y /= scalar;
    }

    public void normalize() {
        double magnitude = getLength();
        x /= magnitude;
        y /= magnitude;
    }

    public int[] getInteger(){
        return new int[]{ (int) x, (int) y};
    }

    public String toString() {
        return "Vector(" + x + ", " + y + ")";
    }
}
