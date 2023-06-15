

import geo.Point_2D;
import geo.Triangle_2D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Triangle_2DTest {
    private final Triangle_2D tri1 = new Triangle_2D(Point_2D.ORIGIN, new Point_2D(5,0), new Point_2D(2.5,3));

    /**
     * tests that getAllPoints functions returns a valid array with the correct points.
     */
    @Test
    void getAllPoints() {
        Point_2D[] ans = tri1.getAllPoints();
        Point_2D[] expected = {new Point_2D(0,0), new Point_2D(5,0), new Point_2D(2.5,3)};
        assertTrue(ans != null);
        assertArrayEquals(expected, ans);
    }

    /**
     * tests that the contain function computes correctly if a point is inside a triangle.
     * tests if a point on the perimeter is inside a triangle.
     */
    @Test
    void contains() {
        assertTrue(tri1.contains(Point_2D.ORIGIN));
        assertTrue(tri1.contains(new Point_2D(2.5,1)));
        assertFalse((tri1.contains(new Point_2D(10,10))));
    }

    /**
     * tests that the area is correct for the default triangle.
     */
    @Test
    void area() {
        double ans = tri1.area();
        assertEquals((double) (3*5)/2, ans);
        tri1.rotate(Point_2D.ORIGIN, 23);
        assertEquals(ans, tri1.area());

    }

    /**
     * tests that the perimeter is computed correctly.
     */
    @Test
    void perimeter() {
        assertEquals((double) 12.81, tri1.perimeter(), 0.001);
    }

    /**
     * tests that the new coordinates of a triangle after moving it, is correct.
     */
    @Test
    void translate() {
        tri1.translate(new Point_2D(10,10));
        Point_2D[] aP = tri1.getAllPoints();
        assertTrue(aP[0].equals(new Point_2D(10,10)));
        assertTrue(aP[1].equals(new Point_2D(15,10)));
        assertTrue(aP[2].equals(new Point_2D(12.5,13)));
    }

    /**
     * tests if the coordinates of a scaled triangle is correct.
     */
    @Test
    void scale() {
    tri1.scale(Point_2D.ORIGIN, 112);
    Point_2D[] aP = tri1.getAllPoints();
    assertTrue(aP[0].equals(new Point_2D(0,0)));
    assertTrue(aP[1].equals(new Point_2D(560.0,0.0)));
    assertTrue(aP[2].equals(new Point_2D(280.0,336.0)));
    }

    /**
     * tests if the coordinates of a rotated triangle is correct.
     */
    @Test
    void rotate() {
        tri1.rotate(Point_2D.ORIGIN,90);
        Point_2D[] aP = tri1.getAllPoints();
        assertTrue(aP[0].equals(Point_2D.ORIGIN));
        assertEquals(aP[1], new Point_2D(3.061616997868383E-16,5.0));
        assertEquals(aP[2], new Point_2D(-3.0000000000000004,2.499999999999999));

    }
}