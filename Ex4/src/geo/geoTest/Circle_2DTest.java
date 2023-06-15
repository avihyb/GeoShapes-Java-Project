
import geo.Circle_2D;
import geo.Point_2D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class Circle_2DTest {
    private final Circle_2D circle1 = new Circle_2D(Point_2D.ORIGIN,4); // Center: (0,0) Radius: 4
    @Test
    void testToString() {
        String ans = circle1.toString();
        assertTrue(ans.equals("(CIRCLE) Center: 0.0;0.0 Radius: 4.0"));
    }

    @Test
    void contains() {
        Point_2D outside = new Point_2D(12,23);
        Point_2D inside = new Point_2D(1,1);
        assertFalse(circle1.contains(outside));
        assertTrue(circle1.contains(inside));
    }

    @Test
    void area() {
        double area = circle1.area();
        assertEquals(50.265482457436691, area, 0.0001);
    }

    @Test
    void perimeter() {
        double perimeter = circle1.perimeter();
        assertEquals(25.1327412287183, perimeter, 0.0001);
    }

    @Test
    void translate() {
        circle1.translate(new Point_2D(10,10));
        Circle_2D correctAns = new Circle_2D(new Point_2D(10,10), circle1.getRadius());
        Circle_2D wrongAns = new Circle_2D(new Point_2D(10,10), 10);
        assertEquals(circle1.getCenter(), correctAns.getCenter());
        assertNotEquals(circle1.getRadius(), wrongAns.getRadius());
        assertEquals(circle1.area(), correctAns.area());
    }

    @Test
    void copy() {
        Circle_2D copy = (Circle_2D) circle1.copy();
        assertEquals(copy.getCenter(), circle1.getCenter());
        assertEquals(copy.getRadius(), copy.getRadius());
    }
    @Test
    void scale() {
        circle1.scale(Point_2D.ORIGIN,0);


    }

    @Test
    void rotate() {
        Circle_2D copy = (Circle_2D) circle1.copy();
        circle1.rotate(Point_2D.ORIGIN,20);
        assertEquals(circle1.getRadius(),copy.getRadius());
        assertEquals(circle1.getCenter(),copy.getCenter());
    }
}