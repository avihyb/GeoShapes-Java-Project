package ex4;

import geo.*;
import gui.Ex4_GUI;
import gui.GUIShape;
import gui.GUI_Shape;
import gui.StdDraw_Ex4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import static jdk.jfr.consumer.EventStream.openFile;

/**
 * 
 * This class is a simple "inter-layer" connecting (aka simplifying) the
 * StdDraw with the Map class.
 * Written for 101 java course it uses simple static functions to allow a 
 * "Singleton-like" implementation.
 * @author boaz.benmoshe
 *
 */
public class Ex4 implements Ex4_GUI {
	private GUI_Shape_Collection _shapes = new ShapeCollection();
	private GUI_Shape _gs;
	private Color _color = Color.blue;
	private boolean _fill = false;
	private String _mode = "";
	private Point_2D _p1;
	private Point_2D _p2;
	private Polygon_2D pol;
	private int tag = 0;

	private static Ex4 _winEx4 = null;

	private Ex4() {
		init(null);
	}

	public void init(GUI_Shape_Collection s) {
		if (s == null) {
			_shapes = new ShapeCollection();
		} else {
			_shapes = s; // TODO: What to do?!
		}// //shou,ld be s.copy();}
		_gs = null;
		_color = Color.blue;
		_fill = false;
		_mode = "";
		_p1 = null;
		pol = null;
	}

	public void show(double d) {
		StdDraw_Ex4.setScale(0, d);
		StdDraw_Ex4.show();
		drawShapes();
	}

	public static Ex4 getInstance() {
		if (_winEx4 == null) {
			_winEx4 = new Ex4();
		}
		return _winEx4;
	}

	public void drawShapes() {
		StdDraw_Ex4.clear();
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shape sh = _shapes.get(i);

			drawShape(sh);
		}
		if (_gs != null) {
			drawShape(_gs);
		}
		StdDraw_Ex4.show();
	}

	private static void drawShape(GUI_Shape g) {
		StdDraw_Ex4.setPenColor(g.getColor());
		if (g.isSelected()) {
			StdDraw_Ex4.setPenColor(Color.gray);
		}
		GeoShape gs = g.getShape();
		boolean isFill = g.isFilled();

		if (gs instanceof Circle_2D) {
			Circle_2D c = (Circle_2D) gs;
			Point_2D cen = c.getCenter();
			double rad = c.getRadius();
			if (isFill) {
				StdDraw_Ex4.filledCircle(cen.x(), cen.y(), rad);
			} else {
				StdDraw_Ex4.circle(cen.x(), cen.y(), rad);
			}
		}
		if(gs instanceof Segment_2D){
			Segment_2D seg = (Segment_2D) gs;
			StdDraw_Ex4.line(seg.get_p1().x(), seg.get_p1().y(), seg.get_p2().x(), seg.get_p2().y());
		}

		if (gs instanceof Polygon_2D || gs instanceof Triangle_2D || gs instanceof Rect_2D) {
			if (gs instanceof Rect_2D) {
				Point_2D[] rectP = ((Rect_2D) gs).getAllPoint();
				double[] xs = {rectP[0].x(), rectP[1].x(), rectP[2].x(), rectP[3].x()};
				double[] ys = {rectP[0].y(), rectP[1].y(), rectP[2].y(), rectP[3].y()};
				if (isFill) {
					StdDraw_Ex4.filledPolygon(xs, ys);
				} else {
					StdDraw_Ex4.polygon(xs, ys);
				}
			} else {
				Polygon_2D pol = (Polygon_2D) gs;
				Point_2D[] allPoints = pol.getAllPoints();
				double[] xs = new double[allPoints.length], ys = new double[allPoints.length];
				for (int i = 0; i < allPoints.length; ++i) {
					xs[i] = allPoints[i].x();
					ys[i] = allPoints[i].y();
				}
				if (isFill) {
					StdDraw_Ex4.filledPolygon(xs, ys);
				} else {
					StdDraw_Ex4.polygon(xs, ys);
				}
			}
		}
	}


	private void setColor(Color c) {
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shape s = _shapes.get(i);
			if (s.isSelected()) {
				s.setColor(c);
			}
		}
	}

	private void setFill() {
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shape s = _shapes.get(i);
			if (s.isSelected()) {
				s.setFilled(_fill);
			}
		}
	}

	public void actionPerformed(String p) {
		_mode = p;
		if (p.equals("Blue")) {
			_color = Color.BLUE;
			setColor(_color);
		}
		if (p.equals("Red")) {
			_color = Color.RED;
			setColor(_color);
		}
		if (p.equals("Green")) {
			_color = Color.GREEN;
			setColor(_color);
		}
		if (p.equals("White")) {
			_color = Color.WHITE;
			setColor(_color);
		}
		if (p.equals("Black")) {
			_color = Color.BLACK;
			setColor(_color);
		}
		if (p.equals("Yellow")) {
			_color = Color.YELLOW;
			setColor(_color);
		}
		// changes the fill flag of geo shape to be true aka filled geo shape
		if (p.equals("Fill")) {
			_fill = true;
			setFill();
		}
		// changes the fill flag of geo shape to be false aka empty shape
		if (p.equals("Empty")) {
			_fill = false;
			setFill();
		}
		if (p.equals("Clear")) {
			_shapes.removeAll();
		}
		if(p.equals("ByAntiToString")) {_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Anti_toString));}
		if(p.equals("ByArea")) {_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Area));}
		if(p.equals("ByAntiArea")) {_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Anti_Area));}
		if(p.equals("ByAntiTag")) {_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Anti_Tag));}
		if(p.equals("ByToString")) {_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_toString));}
		if(p.equals("ByPerimeter")) {_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Perimeter));}
		if(p.equals("ByTag")) {_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Tag));}
		if(p.equals("ByAntiPerimeter")) {_shapes.sort(new ShapeComp(Ex4_Const.Sort_By_Anti_Perimeter));}


		// Unselecting all the shapes
		if(p.equals("None")) {
			for(int i=0;i<_shapes.size();i++) {
				GUI_Shape s = _shapes.get(i);
				s.setSelected(false);
			}
		}

		// Selects all shapes
		if(p.equals("All")) {
			for(int i=0;i<_shapes.size();i++) {
				GUI_Shape s = _shapes.get(i);
				s.setSelected(true);
			}
		}

		// Opposite of selected shapes
		if(p.equals("Anti")) {
			for(int i=0;i<_shapes.size();i++) {
				GUI_Shape s = _shapes.get(i);
				if(s.isSelected()) {
					s.setSelected(false);
				} else {
					s.setSelected(true);
				}
			}

		}

		// removes selected geo shapes
		if(_mode.equals("Remove")) {
			for (int i = _shapes.size() - 1; i >= 0; i--) {
				GUI_Shape s = _shapes.get(i);
				if (s.isSelected()) {
					_shapes.removeElementAt(i);
				}
			}
		}


		// Info about the geo shapes of the current collection
		if(p.equals("Info")) {
			String str = getInfo();
			System.out.println(str);
		}

		// Save: opens a dialog box for the user to submit the desired file name (filename.txt) and and the process continues
		// in a different save function in ShapeCollection
		if(p.equals("Save")) {
				JFrame frame = new JFrame(); // creates a new dialog box in which the user chooses where he wants to save his file
				Object fileName = JOptionPane.showInputDialog(frame, "Enter file's name:"); // dialog box
				_shapes.save((String) fileName); // saves the current file using ShapeCollection save method
		}

		// Load: opens up a dialog box so the user can choose the file he wants to load from
		// the process continues in a load function in ShapeCollection
		if (p.equals("Load")) {
			JFrame frame = new JFrame("Swing Tester");
			JFileChooser fileChooser = new JFileChooser();

			// Set the default directory to the parent directory of the program
			String programPath = System.getProperty("user.dir");
			File programDirectory = new File(programPath).getParentFile(); // set the parent folder of the program to be default of search
			fileChooser.setCurrentDirectory(programDirectory);
			int result = fileChooser.showOpenDialog(frame);
			if (result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				System.out.println("Selected file: " + selectedFile.getAbsolutePath());
				_shapes.load(selectedFile.getAbsolutePath());
				drawShapes();
			}
		}
		drawShapes();

	}


	public void mouseClicked(Point_2D p) {
		System.out.println("Mode: " + _mode + "  " + p);
		// the common of all these shapes is that they require 2 clicks on the UI
		if (_mode.equals("Circle") || _mode.equals("Segment") || _mode.equals("Rect")) {
			if (_gs == null) {
				_p1 = new Point_2D(p);
			} else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
			}
		}

		if (_mode.equals("Triangle")) {
			if(_gs==null) { // if the geo shape is not initialized yet
				pol = new Polygon_2D(); // create a new polygon that will store the future triangle vertexes
				pol.add(p); // adds the first click to be the first vertex of the triangle
				_p1 = new Point_2D(p); // stores the coordinates to global variable
			} else {
				pol.add(p); // adds the next point to the polygon
			}
			if(pol.size() == 3){ // when the polygon reaches the size of 3 (3 vertexes = triangle): it will create the polygon
				Polygon_2D triangle = new Polygon_2D(pol); // creates a temporary polygon
				_gs.setColor(_color);
				_gs = new GUIShape(triangle, _fill, _color, 0);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
				pol = null;
			}
		}

		if (_mode.equals("Polygon")) {
			if(_gs==null) {
				pol = new Polygon_2D(); // creates a new polygon
				pol.add(p); // adds the first click
				_p1 = new Point_2D(p); // stores the first click
			} else { // if the shape already has a first point, add the next one
				pol.add(p);
			}
		}

		//if(_mode.equals("T")) //TODO WHAT IS THIS?! Need to ask boaz

		if(_mode.equals("Move")) {
			if (_p1 == null) {
				_p1 = new Point_2D(p);
			} else {
				move();
				_p1 = null;
			}
		}

		if(_mode.equals("Point")) {
		select(p);
		}

		if(_mode.equals("Copy")) {
			if (_p1 == null) {
				_p1 = new Point_2D(p);
			} else {
				//Point_2D moveVector = new Point_2D(p.x() - _p1.x(), p.y() - _p1.y());
				Point_2D moveVector = new Point_2D(p);
				for (int i = 0; i < _shapes.size(); i++) {
					GUI_Shape s = _shapes.get(i);
					GeoShape g = s.getShape();
					if (s.isSelected() && g != null) {
						GUI_Shape copy = s.copy();
						copy.getShape().translate(moveVector);
						copy.setTag(tag++);             // Setting the tag to a new shape, so it won't copy the old tag
						_shapes.add(copy);}
					_p1 = null;
				}
			}
		}
		if(_mode.equals("Scale_90%")){
			_p1 = new Point_2D(p);
			for(int i=0;i<_shapes.size();i++) {
				GUI_Shape s = _shapes.get(i);
				GeoShape g = s.getShape();
				if(s.isSelected() && g!=null) {
					g.scale(_p1, 0.9);
				}
			}
		}

		// Making it bigger
		if(_mode.equals("Scale_110%")){
			_p1 = new Point_2D(p);
			for(int i=0;i<_shapes.size();i++) {
				GUI_Shape s = _shapes.get(i);
				GeoShape g = s.getShape();
				if(s.isSelected() && g!=null) {
					g.scale(_p1, 1.1);
				}
			}
		}

		if (_mode.equals("Rotate")) {
			if (_p1 == null) {
				_p1 = new Point_2D(p);
			} else {
				_p2 = new Point_2D(p);
				for (int i = 0; i < _shapes.size(); i++) {
					GUI_Shape s = _shapes.get(i);
					GeoShape g = s.getShape();
					if (s.isSelected() && g != null) {
						g.rotate(_p1, Math.toDegrees(_p1.slope(_p2)));
					}
				}
				_p1 = null;
				_p2 = null;
			}
		}
		drawShapes();
	}

	private void select(Point_2D p) {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			GeoShape g = s.getShape();
			if(g!=null && g.contains(p)) {
				s.setSelected(!s.isSelected());
			}
		}
	}

	private void move() { // move function performs the actual translation of the shape
		for(int i=0;i<_shapes.size();i++) { // loops on all the shapes in the container
			GUI_Shape s = _shapes.get(i);
			GeoShape g = s.getShape();
			if(s.isSelected() && g!=null) { // if a shape is selected, it will be translated according to the point
				g.translate(_p1);
			}
		}
	}

	public void mouseRightClicked(Point_2D p) {
		System.out.println("right click!");
		if(_mode.equals("Polygon")){
			Polygon_2D polygon2 = new Polygon_2D(pol);
			_gs = new GUIShape(polygon2, _fill, _color, 0);
			_gs.setTag(tag++);
			_gs.setColor(_color);
			_gs.setFilled(_fill);
			_shapes.add(_gs);
			_gs = null;
			_p1 = null;
			pol = new Polygon_2D();
			drawShapes();
		}
		if(_mode.equals("Circle") || _mode.equals("Rect" )||_mode.equals("Triangle" ) || _mode.equals("Segment")) {
			_gs = null;
			_p1 = null;
			pol = null;
			drawShapes();  // Drawing "nothing" so we can cancel the shape
		}
	}
	public void mouseMoved(MouseEvent e) {
		if(_p1!=null) {
			double x1 = StdDraw_Ex4.mouseX();
			double y1 = StdDraw_Ex4.mouseY();
			GeoShape gs = null;
			//System.out.println("M: "+x1+","+y1);
			Point_2D p = new Point_2D(x1,y1); // Represents the cursor's location in realtime

			if(_mode.equals("Circle")) {
				double r = _p1.distance(p);
				gs = new Circle_2D(_p1,r);
			}
			if(_mode.equals("Segment")){
				double d = _p1.distance(p);
				gs = new Segment_2D(_p1,p);
			}
			if(_mode.equals("Rect")){
				gs = new Rect_2D(_p1, p);
			}
			if(_mode.equals("Triangle")) {
				Polygon_2D temp = new Polygon_2D(pol);
				temp.add(p);
				gs = new Polygon_2D(temp);
				temp.remove(p);
			}
			if(_mode.equals("Polygon")){
				Polygon_2D temp = new Polygon_2D(pol);
				temp.add(p);
				gs = new Polygon_2D(temp);
				temp.remove(p);
			}

			_gs = new GUIShape(gs,false, Color.pink, tag++);
			drawShapes();
		}
	}

	@Override
	public GUI_Shape_Collection getShape_Collection() {
		// TODO Auto-generated method stub
		return this._shapes;
	}

	@Override
	public void show() {show(Ex4_Const.DIM_SIZE); }

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		String ans = "";
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			ans +=s.toString()+"\n";
		}
		return ans;
	}
}
