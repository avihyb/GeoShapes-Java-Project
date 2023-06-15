package geo;

/**
 * This class represents a 2D axis parallel rectangle.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Rect_2D implements GeoShape {
	private Point_2D _max;
	private Point_2D _min;

	/**
	 * ex4.geo.Rect_2D: constructs a rectangle shape from a given topLeft point and bottomRight point.
	 */
	public Rect_2D(Point_2D p1, Point_2D p2) {
		this._min = new Point_2D(Math.min(p1.x(),p2.x()), Math.min(p1.y(),p2.y()));
		this._max = new Point_2D(Math.max(p1.x(),p2.x()), Math.max(p1.y(),p2.y()));
	}

	public Rect_2D(Rect_2D t1) {
		this._max = t1._max;
		this._min = t1._min;
	}
	public double deltaX() {return Math.abs(_max.x() - _min.x());}
	public double deltaY() {return Math.abs(_max.y() - _min.y());}

	public Point_2D[] getAllPoint(){
		Point_2D[] allPoints = new Point_2D[4];
		allPoints[0] = _min;
		allPoints[1] = new Point_2D(_max.x(), _min.y());
		allPoints[2] = _max;
		allPoints[3] = new Point_2D(_min.x(), _max.y());
		return allPoints;
	}
	@Override
	public boolean contains(Point_2D ot) {
		boolean ans = false;
		if (ot.x() >= _min.x() && ot.x() <= _max.x() &&
				ot.y() >= _min.y() && ot.y() <= _max.y()) {
			ans = true;
		}
		return ans;
	}
	@Override
	public double area() {
		return (deltaX()*deltaY());
	}

	@Override
	public double perimeter() {
		return 2*(deltaX()+deltaY());
	}

	@Override
	public void translate(Point_2D vec) {
		_max.move(vec);
		_min.move(vec);
	}

	@Override
	public GeoShape copy() {
		GeoShape rectCopy = (GeoShape) new Rect_2D(this._max, this._min);
		return rectCopy;
	}

	@Override
	public void scale(Point_2D center, double ratio) {
		_max.scale(center, ratio);
		_min.scale(center, ratio);
	}

	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		_max.rotate(center, angleDegrees);
		_min.rotate(center, angleDegrees);
	}

	@Override
	public String toString(){
		return "(RECTANGLE): p1: "+getAllPoint()[0]+" p2: "+getAllPoint()[1]+" p3: "+getAllPoint()[2]+" p4: "+getAllPoint()[3];
	}
}
