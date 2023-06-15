

import geo.GeoShape;
import geo.Point_2D;
import geo.Segment_2D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Segment_2DTest {

    private final Segment_2D mySegment = new Segment_2D(Point_2D.ORIGIN, new Point_2D(5,5));
    @Test
    void testToString() {
        String ans = mySegment.toString();
        System.out.println(ans);
        assertTrue(ans.equals("(SEGMENT) pointA: 0.0;0.0 pointB: 5.0;5.0"));
    }

    @Test
    void contains() {
        boolean ans;
        try{
            ans = mySegment.contains(Point_2D.ORIGIN);
        }
        catch(RuntimeException e){
            System.out.println("Successfully threw an error to check if a segment can contain.");
            ans = true;
        }
    assertTrue(ans);
    }

    @Test
    void area() {
        assertEquals(0, mySegment.area());
    }

    @Test
    void perimeter() {
        double myPerimeter = 2*mySegment.get_p1().distance(mySegment.get_p2());
        double ans = mySegment.perimeter();
        assertEquals(myPerimeter, ans);
    }

    @Test
    void translate() {
        Point_2D pointA = new Point_2D(2, 3);
        Point_2D pointB = new Point_2D(6, 4);
        Segment_2D segment = new Segment_2D(pointA, pointB);
        Point_2D translationVector = new Point_2D(3, 2);
        segment.translate(translationVector);
        // Check if the segment has been correctly moved
        Point_2D expectedPointA = new Point_2D(5, 5);
        Point_2D expectedPointB = new Point_2D(9, 6);
        assertEquals(expectedPointA, segment.get_p1());
        assertEquals(expectedPointB, segment.get_p2());

    }

    @Test
    void copy() {
        GeoShape copy = mySegment.copy();
        assertEquals(copy.perimeter(),mySegment.perimeter());
    }

    @Test
    void scale() {

    }

    @Test
    void rotate() {
    }
}