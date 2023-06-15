

import geo.Point_2D;
import geo.Polygon_2D;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class Polygon_2DTest {

    private final Polygon_2D polygon1 = new Polygon_2D();

    @Test
    void getAllPoints() {
    }

    /**
     * testAdd tests that creating a new polygon in this order:
     * (0,0) -> (5,0) -> (2.5,3) is possible (no intersections).
     * Also tests that a 4th point (1,-1) is not added. (because it intersects)
     */
    @Test
    void add() {
            Polygon_2D polygon = new Polygon_2D();
            Point_2D p1 = new Point_2D(0, 0);
            Point_2D p2 = new Point_2D(5, 0);
            Point_2D p3 = new Point_2D(2.5, 3);
            // Add initial points to the polygon
            polygon.add(p1);
            polygon.add(p2);
            polygon.add(p3);
            // Verify that the initial points are present in the polygon
            assertTrue(polygon.isVertex(p1));
            assertTrue(polygon.isVertex(p2));
            assertTrue(polygon.isVertex(p3));
            // Add a point that does not cause intersections
            Point_2D disjointPoint = new Point_2D(1, 1);
            polygon.add(disjointPoint);
            // Verify that the not intersecting point was added successfully
            assertTrue(polygon.isVertex(disjointPoint));
            // Add a point that causes intersections
            Point_2D intersectingPoint = new Point_2D(1,-1);
            polygon.add(intersectingPoint);
            // Verify that the intersecting point was not added
            assertFalse(polygon.isVertex(intersectingPoint));
            // Verify that the initial points are still present in the polygon
            assertTrue(polygon.isVertex(p1));
            assertTrue(polygon.isVertex(p2));
            assertTrue(polygon.isVertex(p3));
    }

    @Test
    void testToString() {
        polygon1.add(Point_2D.ORIGIN);
        polygon1.add(new Point_2D(15,25));
        String ans = polygon1.toString();
        String expected = "(RECTANGLE): Point 0: 0.0;0.0 Point 1: 15.0;25.0 ";
        assertTrue(ans.equals(expected));
    }

    @Test
    void contains() {
        assertFalse(polygon1.contains(Point_2D.ORIGIN));
        polygon1.add(Point_2D.ORIGIN);
        polygon1.add(new Point_2D(10,0));
        polygon1.add(new Point_2D(10,10));
        polygon1.add(new Point_2D(0,10));
        assertTrue(polygon1.contains(new Point_2D(5,5)));
    }
    /**
     * testArea tests that a 10x10 polygon (square) = 100
     * tests a complex polygon accuracy with a precomputed area.
     */
    @Test
    void area() {
        polygon1.add(new Point_2D(0,0));
        polygon1.add(new Point_2D(10,0));
        polygon1.add(new Point_2D(10,10));
        polygon1.add(new Point_2D(0,10));
        assertEquals(100.0,polygon1.area(),0.001);
        Polygon_2D complex = new Polygon_2D();
        complex.add(new Point_2D(-1,-1));
        complex.add(new Point_2D(3,-2));
        complex.add(new Point_2D(10,10));
        complex.add(new Point_2D(8,9));
        complex.add(new Point_2D(2,3));
        complex.add(new Point_2D(3.5,2));
        assertEquals(31.5,complex.area(),0.001);
    }

    /**
     * tests that the perimeter of a 10*10 square is 40
     *
     */
    @Test
    void perimeter() {
        polygon1.add(new Point_2D(0,0));
        polygon1.add(new Point_2D(10,0));
        polygon1.add(new Point_2D(10,10));
        polygon1.add(new Point_2D(0,10));
        assertEquals(40,polygon1.perimeter(), 0.001);
    }

    @Test
    void translate() {
        Polygon_2D complex = new Polygon_2D();
        complex.add(new Point_2D(-1,-1));
        complex.add(new Point_2D(3,-2));
        complex.add(new Point_2D(10,10));
        complex.add(new Point_2D(8,9));
        complex.add(new Point_2D(2,3));
        complex.add(new Point_2D(3.5,2));
        complex.translate(Point_2D.ORIGIN);

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