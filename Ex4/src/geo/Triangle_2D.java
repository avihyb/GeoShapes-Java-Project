package geo;

/**
 * This class represents a 2D Triangle in the plane.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Triangle_2D implements GeoShape{
	private Point_2D _p1;
	private Point_2D _p2;
	private Point_2D _p3;

	/**
	 * ex4.geo.Triangle_2D: constructs a triangle shape by 3 given 2D points.
	 * @param p1
	 * @param p2
	 * @param p3
	 */
	public Triangle_2D(Point_2D p1, Point_2D p2, Point_2D p3) {
		this._p1 = new Point_2D(p1);
		this._p2 = new Point_2D(p2);
		this._p3 = new Point_2D(p3);
	}

	/**
	 * ex4.geo.Triangle_2D: copy constructor.
	 * @param t1
	 */
	public Triangle_2D(Triangle_2D t1) {
		Point_2D[] allPoints = t1.getAllPoints();
		this._p1 = allPoints[0];
		this._p2 = allPoints[1];
		this._p3 = allPoints[2];
	}

	/**
	 * getAllPoints: returns an array consisting of all the triangle's points.
	 * @return array with the all of the triangle's points
	 */
	public Point_2D[] getAllPoints() {
		Point_2D[] ans = new Point_2D[3];
		ans[0] = this._p1;
		ans[1] = this._p2;
		ans[2] = this._p3;
		return ans;
	}

	/**
	 * contain: checks if a point is inside a triangle by computing
	 * three triangles' area created with the given point and the original triangle point
	 * and if one of the areas is greater than the original area - the point isn't within the triangle.
	 * @param ot - a query 2D point
	 * @return true if the point is inside the triangle
	 */
	@Override
	public boolean contains(Point_2D ot) {
		boolean ans = false;
		double originalArea = this.area();
		Triangle_2D t1 = new Triangle_2D(ot, _p2, _p3);
		Triangle_2D t2 = new Triangle_2D(_p1, ot, _p3);
		Triangle_2D t3 = new Triangle_2D(_p1, _p2, ot);
		if(t1.area() <= originalArea && t2.area() <= originalArea && t3.area() <= originalArea)
			ans = true;
		return ans;
	}

	/** @area: computes the area of the triangle.
	 * The area of triangle in coordinate geometry is calculated
	 * by the formula (1/2)*|x1(y2 − y3) + x2(y3 − y1) + x3(y1 − y2)|, where (x1, y1), (x2, y2),
	 * and (x3, y3) are the vertices of the triangle. (from cuemath.com)
	 * @return
	 */
	@Override
	public double area() {
		return 0.5*Math.abs(_p1.x()*(_p2.y() - _p3.y()) + _p2.x()*(_p3.y() - _p1.y()) + _p3.x()*(_p1.y() - _p2.y()));
	}

	/**
	 * perimeter: computes the perimeter of a triangle by summing the 3 distances
	 * from each point of the triangle.
	 * @return double perimeter
	 */
	@Override
	public double perimeter() {
		return _p1.distance(_p2) + _p2.distance(_p3) + _p3.distance(_p1);
	}

	/**
	 * translate: moves the triangle to given vector.
	 * @param vec - a vector from the 0,0
	 */
	@Override
	public void translate(Point_2D vec) {
	_p1.move(vec);
	_p2.move(vec);
	_p3.move(vec);
	}

	/**
	 * copy: creates a copy of the triangle.
	 * @return
	 */
	@Override
	public GeoShape copy() {
		GeoShape copy = (GeoShape) new Triangle_2D(this);
		return copy;
	}

	/**
	 * scale: scales the triangle by a given center point and ratio.
	 * @param center - center point from which the rescaling is being done.
	 * @param ratio - the ratio of rescaling.
	 */
	@Override
	public void scale(Point_2D center, double ratio) {
	_p1.scale(center, ratio);
	_p2.scale(center, ratio);
	_p3.scale(center, ratio);
	}

	/**
	 * rotate: rotates the triangle by a given center point and angle.
	 * @param center - center point from which the rotation is being done.
	 * @param angleDegrees - the angle (in Degrees) the shape should be rotated by.
	 */
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
	_p1.rotate(center, angleDegrees);
	_p2.rotate(center, angleDegrees);
	_p3.rotate(center, angleDegrees);

	}
}
