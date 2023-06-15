

import geo.GeoShape;
import geo.Point_2D;
import geo.Polygon_2D;
import geo.Rect_2D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Rect_2DTest {
    private final Rect_2D rect1 = new Rect_2D(Point_2D.ORIGIN,new Point_2D(5,5)); //Square
    private final Rect_2D rect2 = new Rect_2D(new Point_2D(6,6), new Point_2D(10,16));
    /**
     * tests several cases of points inside/outside a rectangle
     */
    @Test
    void contains() {
        assertTrue(rect1.contains(Point_2D.ORIGIN)); // Border point
        assertFalse(rect1.contains(new Point_2D(100,100))); // Extremely out of bounds
        assertTrue(rect2.contains(new Point_2D(7,7))); // Obvious within point
        assertFalse(rect2.contains(Point_2D.ORIGIN)); // Obvious outer point
    }

    @Test
    void area() {
        assertEquals(25,rect1.area());
    }

    @Test
    void perimeter() {
        assertEquals(20, rect1.perimeter());
    }

    @Test
    void translate() {


    }

    @Test
    void copy() {
    }

    @Test
    void scale() {
    }

    @Test
    void rotate() {
    }
}