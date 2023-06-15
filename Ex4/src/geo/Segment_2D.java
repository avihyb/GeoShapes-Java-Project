package geo;

/**
 * This class represents a 2D segment on the plane, 
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Segment_2D implements GeoShape{
	private Point_2D _a;
	private Point_2D _b;
	/** Constructor: gets 2 points and creates a Segment.
 	**/
	public Segment_2D(Point_2D a, Point_2D b) {
		this._a = a;
		this._b = b;
	}
	/** Constructor: gets a Segment a creates another one out of it.
	 **/
	public Segment_2D(Segment_2D t1) {
		this._a = t1.get_p1();
		this._b = t1.get_p2();
	}
	/** @get_p1 returns the A point of a segment.
	 **/
	public Point_2D get_p1() {return _a;}
	/** @get_p2 returns the B point of a segment.
	 **/
	public Point_2D get_p2() {return _b;}
	/** @toString returns a string describing the ex4.geo.GeoShape:
	 * (SHAPE TYPE): INFO
	 * e.g: (SEGMENT): pointA: (x1,y1) pointB: (x2,y2)
	 **/
	@Override
	public String toString(){
		return "(SEGMENT) pointA: "+_a.toString()+" pointB: "+_b.toString();
	}
	/** @contains returns true iff a point is inside a closed shape only.
	 **/
	@Override
	public boolean contains(Point_2D ot) {
		throw new RuntimeException("This isn't a closed shape!");
	}
	/** @area: computes the area of a geometrical shape.
	 * for segment, it will return 0 (as it has no area).
	 **/
	@Override
	public double area() {
		return 0;
	}
	/** @perimeter: computes the perimeter of a geometrical shape.
	 * for segment, it will return twice the length of the segment.
	 **/
	@Override
	public double perimeter() {
		return 2*_a.distance(_b);
	}
	/** @translate: changes the coordinates of the geometrical shape,
	 * based on a given vector. For segement, it will compute 2 new
	 * end points based on the original end points + the given vector.
	 **/
	@Override
	public void translate(Point_2D vec) {
		_a.move(vec);
		_b.move(vec);
	}
	/** @copy: creates a deep copy of a geometrical shape.
	 **/
	@Override
	public GeoShape copy() {
		GeoShape segmentCopy = new Segment_2D(this);
		return segmentCopy;
	}
	/** @scale: changes the size of the geometrical shape based
	 *  on a given center point and ratio.
	 **/
	@Override
	public void scale(Point_2D center, double ratio) {
		_a.scale(center, ratio);
		_b.scale(center, ratio);
	}
	/** @rotate: rotates the geometrical shape (changes coordinates)
	 * based on given center point and angle degree.
	 **/
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		_a.rotate(center, angleDegrees);
		_b.rotate(center, angleDegrees);
	}
}