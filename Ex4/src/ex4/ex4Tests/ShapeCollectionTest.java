import ex4.ShapeCollection;
import geo.*;
import gui.GUIShape;
import gui.GUI_Shape;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ShapeCollectionTest {
    private final ShapeCollection myColec = new ShapeCollection();
    private final Point_2D p1 = new Point_2D(1,1);
    private final Point_2D p2 = new Point_2D(5,7);
    private final Point_2D p3 = new Point_2D(-1,0);
    private final Rect_2D rect = new Rect_2D(Point_2D.ORIGIN, new Point_2D(5,5));
    private final Circle_2D circ = new Circle_2D(Point_2D.ORIGIN, 4);
    private final Triangle_2D tri = new Triangle_2D(p1, p2, p3);
    private final Segment_2D seg = new Segment_2D(Point_2D.ORIGIN, p2);
    private final Polygon_2D pol = new Polygon_2D();
    private final GUIShape square = new GUIShape(rect,true, Color.BLACK,1);
    private final GUIShape circle = new GUIShape(circ, true, Color.RED, 2);
    private final GUIShape triangle = new GUIShape(tri, true, Color.GREEN,3);

    private final GUIShape segment = new GUIShape(seg, true, Color.BLACK, 4);
    private final GUIShape polygon = new GUIShape(pol, true, Color.CYAN,5);


    @Test
    void removeElementAt() {
        myColec.add(square);
        myColec.removeElementAt(0);
        assertEquals(0, myColec.size());
        myColec.add(square);
        myColec.add(circle);
        myColec.add(triangle);
        myColec.add(segment);
        myColec.removeElementAt(1);
        assertTrue(myColec.get(1).equals(segment));
    }

    @Test
    void addAt() {
        myColec.add(square);
        myColec.add(circle);
        myColec.add(triangle);
        myColec.add(segment);
        myColec.addAt(polygon, 2);
        assertTrue(myColec.get(2).equals(polygon));
        try{
            myColec.addAt(segment,300);
        }
        catch (RuntimeException e){

        }
    }

    @Test
    void add() {
        myColec.add(null);
        myColec.add(square);
        assertTrue(myColec.size() == 1);
    }

    @Test
    void copy() {
        myColec.add(square);
        myColec.add(circle);
        myColec.add(triangle);
        myColec.add(segment);
        myColec.addAt(polygon, 2);
        ShapeCollection copy = (ShapeCollection) myColec.copy();
        for (int i = 0; i < myColec.size(); i++) {
            assertTrue(copy.get(i).equals(myColec.get(i)));
        }
    }

    @Test
    void sort() {
        // TODO HOW TO TEST SORT?
    }

    @Test
    void removeAll() {
        myColec.add(square);
        myColec.add(circle);
        myColec.add(triangle);
        myColec.add(segment);
        myColec.addAt(polygon, 2);
        myColec.removeAll();
        assertTrue(myColec.size() == 0);

    }

    @Test
    void save() {
        // How to test save?
    }

    @Test
    void load() {
        myColec.load("C:\\Users\\User\\IdeaProjects\\Ex4\\polygon.txt");
        assertTrue(myColec.size() == 1);
        assertEquals(myColec.get(0).getTag(), 66);
        assertTrue(myColec.get(0).getShape() instanceof Polygon_2D);
        assertTrue(myColec.get(0).getColor().getRGB() == -16776961);
        assertTrue(((Polygon_2D) myColec.get(0).getShape()).getAllPoints()[0].equals(new Point_2D(3.921875,8.46875)));
        assertTrue(((Polygon_2D) myColec.get(0).getShape()).getAllPoints()[1].equals(new Point_2D(8.125,6.6875)));
        assertTrue(((Polygon_2D) myColec.get(0).getShape()).getAllPoints()[2].equals(new Point_2D(7.578125,2.890625)));
        assertTrue(((Polygon_2D) myColec.get(0).getShape()).getAllPoints()[3].equals(new Point_2D(4.015625,1.953125)));
        assertTrue(((Polygon_2D) myColec.get(0).getShape()).getAllPoints()[4].equals(new Point_2D(1.234375,6.140625)));
    }

    @Test
    void testToString() {
        myColec.removeAll();
        myColec.add(square);
        myColec.add(circle);
        myColec.add(triangle);
        myColec.add(segment);
        myColec.addAt(polygon, 2);
        String ans = myColec.toString();
        assertTrue("(RECTANGLE): p1: 0.0;0.0 p2: 5.0;0.0 p3: 5.0;5.0 p4: 0.0;5.0(CIRCLE) Center: 0.0;0.0 Radius: 4.0geo.Triangle_2D@3e3047e6(SEGMENT) pointA: 0.0;0.0 pointB: 5.0;7.0".equals(ans));
    }
}