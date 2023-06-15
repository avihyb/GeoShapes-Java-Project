package geo;

/**
 * This class represents a 2D circle in the plane. 
 * Please make sure you update it according to the ex4.geo.GeoShape interface.
 * Ex4: you should update this class!
 * @author boaz.benmoshe
 *
 */
public class Circle_2D implements GeoShape{
	private Point_2D _center;
	private double _radius;

	/**
	 * Circle_2D: constructs a circle shape from a given center point and radius.
	 * @param cen
	 * @param rad
	 */
	public Circle_2D(Point_2D cen, double rad) {
		this._center = new Point_2D(cen);
		this._radius = rad;
	}

	/**
	 * getRadius: returns the radius of the circle.
	 * @return
	 */
	public double getRadius() {return this._radius;}

	/**
	 * getCenter: returns the center point of the circle.
	 * @return
	 */
	public Point_2D getCenter(){ return _center;}

	/** @toString returns a string describing the ex4.geo.GeoShape:
	 * (SHAPE TYPE|NAME): INFO
	 * e.g: (CIRCLE|CIRCLE): Center: [x,y] Radius: r
	 **/
	 @Override
	 public String toString()
	    {
			return "(CIRCLE) Center: "+_center+" "+"Radius: "+_radius;
		}

	/**
	 * @contains: returns true iff the given point is within the circle.
	 * Circle contains a point iff the distance from the point to the center is
	 * less or equal to the radius.
	 * @param ot - a query 2D point
	 * @return
	 */
	@Override
	public boolean contains(Point_2D ot) {
		boolean ans = false;
		if(ot.distance(_center) <= _radius){
			ans = true;
		}
		return ans;
	}

	/**
	 * @area: computes the area of circle (PI*(Radius^2)).
	 * @return area of the circle
	 */
	@Override
	public double area() {
		double area = Math.PI*Math.pow(_radius,2);
		return area;
	}

	/**
	 * @perimeter: returns the perimeter of a circle (2*PI*Radius).
	 * @return perimeter of a circle
	 */
	@Override
	public double perimeter() {
		return 2*Math.PI*_radius;
	}

	/**
	 * @translate: changes the coordinates of the circle, based on a given vector.
	 * @param vec - a vector from the 0,0
	 */
	@Override
	public void translate(Point_2D vec) {
		_center = vec;
	}

	/**
	 * @copy: computes a deep copy of the circle.
	 * @return deep copy of the circle
	 */
	@Override
	public GeoShape copy() {
		GeoShape circleCopy = new Circle_2D(_center,_radius);
		return circleCopy;
	}

	/**
	 * @scale: scales the circle by a given center point and ratio.
	 * @param center - center point from which the rescaling is being done.
	 * @param ratio - the ratio of rescaling.
	 */
	@Override
	public void scale(Point_2D center, double ratio) {
		center.scale(center, ratio);
		this._radius *= ratio;
	}

	/**
	 * @rotate: rotates the circle. (circle is round so rotation is meaningless).
	 * @param center - center point from which the rotation is being done.
	 * @param angleDegrees - the angle (in Degrees) the shape should be rotated by.
	 */
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		_center.rotate(center,angleDegrees); // Should do NOTHING.
	}

}
