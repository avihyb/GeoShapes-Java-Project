package geo;

import java.util.ArrayList;

public class Polygon_2D implements GeoShape{
	private ArrayList<Point_2D> _polygon;

	public Polygon_2D() {_polygon = new ArrayList<>();}

	public Polygon_2D(Polygon_2D po) {this._polygon = new ArrayList<>(po._polygon);}


	public Point_2D[] getAllPoints() {
		Point_2D[] allPoints = new Point_2D[_polygon.size()];
		for (int i = 0; i < _polygon.size(); i++) {
			allPoints[i] = _polygon.get(i);
		}
		return allPoints;
	}

	public int size(){
		return _polygon.size();
	}

	public Point_2D get(int i){
		return _polygon.get(i);
	}

	/**
	 * add: adds a 2D point to a polygon if and only if it doesn't
	 * create a segment that collides with the prior polygon's segments.
	 * @param p desired point
	 */
	public void add(Point_2D p) {
		if(makesIntersections(p)){
			return;}
		_polygon.add(p);
	}

	/**
	 * makeIntersections: checks if a point can potentially create intersections
	 * inside the polygon. For polygons that have less than 2 anchor points, the new point
	 * must not be one of the original points (otherwise the polygon will be a segment/point).
	 * For 2 anchor points or more, the function checks if one of the polygon's segments intersect
	 * the segment that'll be formed with new point and the last point.
	 * @param newP
	 * @return true if the new point is creating collision in the polygon
	 */
	private boolean makesIntersections(Point_2D newP){
		boolean ans = false;
		if(_polygon.size() == 1){
			if(newP.equals(_polygon.get(0))){
				ans = true;}}
		if(_polygon.size() == 2){
			if(newP.equals(_polygon.get(0)) && newP.equals(_polygon.get(1))){
				ans = true;}}
		if(_polygon.size() > 2){
			for (int i = 0; i < _polygon.size() - 2; i++) {
				Point_2D p1 = _polygon.get(i);
				Point_2D p2 = _polygon.get(i+1);
				Point_2D p3 = _polygon.get(_polygon.size() - 1);
				if(isIntersecting(p1, p2, p3, newP)){
					ans = true;
					break;
				}
			}
		}
		return ans;
	}

	/**
	 * isIntersecting: checking if 2 segments create a collision point using delta calculation.
	 * Idea taken from https://stackoverflow.com/questions/25830932/how-to-find-if-two-line-segments-intersect-or-not-in-java.
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param newP
	 * @return true if the segments intersect
	 */
	private boolean isIntersecting(Point_2D p1, Point_2D p2, Point_2D p3, Point_2D newP){
		double det = (p2.x() - p1.x()) * (newP.y() - p3.y()) - (newP.x() - p3.x()) * (p2.y() - p1.y());
		if (det == 0)
			return false; //Lines are parallel
		double lambda = ((newP.y() - p3.y()) * (newP.x() - p1.x()) + (p3.x() - newP.x()) * (newP.y() - p1.y())) / det;
		double gamma = ((p1.y() - p2.y()) * (newP.x() - p1.x()) + (p2.x() - p1.x()) * (newP.y() - p1.y())) / det;
		return (0 < lambda && lambda < 1) && (0 < gamma && gamma < 1);
	}

	public void remove(Point_2D p){
		_polygon.remove(p);
	}

	/**
	 * toString: creates a string with all points coordinates.
	 * @return string representing all polygon's points
	 */

	@Override
	public String toString() {
		String ans = "";
		System.out.println(_polygon.size());
		if(_polygon.size() > 4) {ans += "(POLYGON):";} if(_polygon.size() == 3){ans += "(TRIANGLE): ";} if(_polygon.size() == 2){ans += "(RECTANGLE): ";}
		for (int i = 0; i < _polygon.size(); i++) {
			ans += "Point " + i + ": " + _polygon.get(i).toString() + " ";
		}
		return ans;

	}

	/**
	 * contains: checks if a point falls inside the polygon.
	 * @param ot - a query 2D point
	 * @return true if the point is within the polygon
	 */
	@Override
	public boolean contains(Point_2D ot) {
		boolean inside = false;
		int n = _polygon.size();
		for (int i = 0, j = n - 1; i < n; j = i++) {
			Point_2D vertex1 = _polygon.get(i);
			Point_2D vertex2 = _polygon.get(j);

			if (((vertex1.y() > ot.y()) != (vertex2.y() > ot.y())) &&
					(ot.x() < (vertex2.x() - vertex1.x()) * (ot.y() - vertex1.y()) /
							(vertex2.y() - vertex1.y()) + vertex1.x())) {
				inside = !inside;
			}
		}
		return inside;
	}


	public boolean isVertex(Point_2D pt){
		for (int i = 0; i < _polygon.size(); i++) {
			if(_polygon.get(i).equals(pt))
				return true;
		}
		return false;
	}

	/**
	 * area: computes polygon's area using
	 * @return polygon's area
	 */
	@Override
	public double area() {
		double area = 0;
		if(_polygon.size() > 2) {
			for (int i = 0; i < _polygon.size(); i++) {
				Point_2D currentPoint = _polygon.get(i);
				Point_2D nextPoint = _polygon.get((i + 1) % _polygon.size());

				double x1 = currentPoint.x();
				double y1 = currentPoint.y();
				double x2 = nextPoint.x();
				double y2 = nextPoint.y();

				area += (x1 * y2 - x2 * y1);
			}
			area = Math.abs(area / 2);
		}
		return area;
	}

	@Override
	public double perimeter() {
		int perimeter = 0;
		// summing up all the polygon's segments
		for (int i = 0; i < _polygon.size()-1; i++) {
			Point_2D p1 = _polygon.get(i);
			Point_2D p2 = _polygon.get(i+1);
			perimeter += p1.distance(p2);
		}
		// adding the last segment of the polygon
		Point_2D lastP = new Point_2D(_polygon.get(_polygon.size()-1));
		Point_2D firstP = new Point_2D(_polygon.get(0));
		return perimeter += lastP.distance(firstP);
	}

	@Override
	public void translate(Point_2D vec) {
		for (int i = 0; i < _polygon.size(); i++) {
			_polygon.get(i).move(vec);
		}
	}

	@Override
	public GeoShape copy() {
		GeoShape copy = (Polygon_2D) new Polygon_2D(this);
		return copy;
	}

	@Override
	public void scale(Point_2D center, double ratio) {
		for (int i = 0; i < _polygon.size(); i++) {
			_polygon.get(i).scale(center, ratio);
		}
	}

	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		for (int i = 0; i < _polygon.size(); i++) {
			_polygon.get(i).rotate(center, angleDegrees);
		}
	}

}
