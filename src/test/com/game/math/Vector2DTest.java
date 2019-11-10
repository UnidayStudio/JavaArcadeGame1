package test.com.game.math;
/*
import main.com.game.math.Vector2D;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Vector2DTest {
    private static final double FLOATING_POINT_DELTA = 0.1;

    @Test
    void set() {
        Vector2D vec = new Vector2D();

        vec.set(1.0, 2.0);

        assertEquals(1.0, vec.x, FLOATING_POINT_DELTA);
        assertEquals(2.0, vec.y, FLOATING_POINT_DELTA);

        vec.set(new Vector2D(-1.0, 3.0));

        assertEquals(-1.0, vec.x, FLOATING_POINT_DELTA);
        assertEquals(3.0, vec.y, FLOATING_POINT_DELTA);
    }

    @Test
    void getLength() {
        Vector2D vec = new Vector2D(0.0, 5.0);

        assertEquals(5.0, vec.getLength(), FLOATING_POINT_DELTA);
    }

    @Test
    void add() {
        Vector2D vec = new Vector2D(0.0, 0.0);

        vec.add(1.0, 7.5);

        assertEquals(1.0, vec.x, FLOATING_POINT_DELTA);
        assertEquals(7.5, vec.y, FLOATING_POINT_DELTA);

        vec.add(new Vector2D(0.5, 3.0));

        assertEquals(1.5, vec.x, FLOATING_POINT_DELTA);
        assertEquals(10.5, vec.y, FLOATING_POINT_DELTA);
    }

    @Test
    void subtract() {
        Vector2D vec = new Vector2D(10.0, 10.0);

        vec.subtract(1.0, 7.5);

        assertEquals(9.0, vec.x, FLOATING_POINT_DELTA);
        assertEquals(2.5, vec.y, FLOATING_POINT_DELTA);

        vec.subtract(new Vector2D(0.5, 3.0));

        assertEquals(8.5, vec.x, FLOATING_POINT_DELTA);
        assertEquals(-0.5, vec.y, FLOATING_POINT_DELTA);
    }

    @Test
    void multiply() {
        Vector2D vec = new Vector2D(10.0, 5.0);

        vec.multiply(2.0);

        assertEquals(20.0, vec.x, FLOATING_POINT_DELTA);
        assertEquals(10.0, vec.y, FLOATING_POINT_DELTA);
    }

    @Test
    void divide() {
        Vector2D vec = new Vector2D(20.0, 10.0);

        vec.divide(2.0);

        assertEquals(10.0, vec.x, FLOATING_POINT_DELTA);
        assertEquals(50.0, vec.y, FLOATING_POINT_DELTA);
    }

    @Test
    void normalize() {
        Vector2D vec = new Vector2D(20.0, 0.0);

        vec.normalize();

        assertEquals(1.0, vec.x, FLOATING_POINT_DELTA);
        assertEquals(0.0, vec.y, FLOATING_POINT_DELTA);
    }

    @Test
    void getInteger() {
        Vector2D vec = new Vector2D(1.16, 2.758);

        int[] result = vec.getInteger();

        assertEquals(1, result[0]);
        assertEquals(2, result[1]);
    }

    @Test
    void testToString() {
        Vector2D vec = new Vector2D(20.0, 0.0);
        //
        assertEquals("Vector(20.0, 0.0)", vec.toString());

    }
}*/