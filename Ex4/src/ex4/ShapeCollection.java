package ex4;

import geo.*;
import gui.GUIShape;
import gui.GUI_Shape;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;


/**
 * This class represents a collection of GUI_Shape.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class ShapeCollection implements GUI_Shape_Collection {
	private ArrayList<GUI_Shape> _shapes;

	public ShapeCollection() {
		_shapes = new ArrayList<GUI_Shape>();
	}

	@Override
	public GUI_Shape get(int i) {
		return _shapes.get(i);
	}

	@Override
	public int size() {
		return _shapes.size();
	}

	@Override
	public GUI_Shape removeElementAt(int i) {
		GUI_Shape ans = null;
		if (i >= 0 && i <= size()) {
			ans = _shapes.get(i);
			_shapes.remove(i);
			for (int j = i; j < size() - 1; j++) {
				_shapes.set(j, _shapes.get(j + 1));
			}
		}
		return ans;
	}

	@Override
	public void addAt(GUI_Shape s, int i) {
		if (i < 0 || i > size()) {
			throw new RuntimeException("ERROR: index invalid");
		}
		_shapes.add(i, s);
	}

	@Override
	public void add(GUI_Shape s) {
		if (s != null && s.getShape() != null) {
			_shapes.add(s);
		}
	}

	@Override
	public GUI_Shape_Collection copy() {
		GUI_Shape_Collection copy = new ShapeCollection();
		for (int i = 0; i < _shapes.size(); i++) {
			copy.add(_shapes.get(i));
		}
		return copy;
	}

	@Override
	public void sort(Comparator<GUI_Shape> comp) {
		_shapes.sort(comp);
	}

	@Override
	public void removeAll() {
		_shapes.removeAll(_shapes);
	}

	@Override
	public void save(String file) {
		try {
			FileWriter myWriter = new FileWriter((String) file + ".txt");
			for (int i = 0; i < _shapes.size(); i++) {
				GUI_Shape shape = _shapes.get(i);
				GeoShape geos = shape.getShape();
				myWriter.write(geos.getClass().getSimpleName()+","); // SHAPE TYPE (Object name)
				if(geos instanceof Circle_2D){ // RELEVANT POINTS FOR CIRCLE
					myWriter.write(((Circle_2D) geos).getCenter()+","+((Circle_2D) geos).getRadius()+",");
				} if(geos instanceof Polygon_2D){ // RELEVANT POINTS FOR POLYGON/TRIANGLE
					for (int j = 0; j < ((Polygon_2D) geos).size(); j++) {
						myWriter.write(((Polygon_2D) geos).get(j).toString()+",");
					}
				}
				if(geos instanceof Rect_2D){
					myWriter.write(((Rect_2D) geos).getAllPoint()[0].toString()+","+((Rect_2D) geos).getAllPoint()[1].toString()+","+((Rect_2D) geos).getAllPoint()[2].toString()+","+((Rect_2D) geos).getAllPoint()[3].toString()+",");
				}
				if(geos instanceof Segment_2D){
					myWriter.write(((Segment_2D) geos).get_p1().toString()+","+((Segment_2D) geos).get_p2().toString()+",");
				}
				myWriter.write(shape.getColor().getRGB()+",");
				myWriter.write(shape.isFilled()+",");
				myWriter.write(shape.getTag()+"\n");
			}
			myWriter.close();
			System.out.println("Success");
		} catch (
				IOException e) {
			System.out.println("Error Saving");
			e.printStackTrace();
		}
	}

	@Override
	public void load(String file)  {
		//_shapes.removeAll(_shapes);
		try {
			File myObj = new File(file);
			Scanner myReader = new Scanner(myObj);
			int i=0;
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				System.out.println(i+") "+data);
				i=i+1;
				String[] shapesData = data.split(","); // Splits the Info line into array [SHAPENAME,p1,...,pn,RGBCode,isFill,tagInt]

				if(shapesData[0].equals("Circle_2D")){
					String[] centerData = shapesData[1].split(";");
					Point_2D centerPoint = new Point_2D(Double.parseDouble(centerData[0]),Double.parseDouble(centerData[1]));
					Color color = new Color((Integer.parseInt(shapesData[3])));
					Circle_2D circle = new Circle_2D(centerPoint, Double.parseDouble(shapesData[2]));
					boolean isFill = shapesData[4].equals("true");
					GUIShape circleGUI = new GUIShape(circle, isFill, color,Integer.parseInt(shapesData[5]));
					_shapes.add(circleGUI);
				}
				/** If the shape can be created as a polygon**/
				if(shapesData[0].equals("Segment_2D") || shapesData[0].equals("Triangle_2D")
						|| shapesData[0].equals("Polygon_2D") || shapesData[0].equals("Rect_2D")){
					int tag = Integer.parseInt(shapesData[shapesData.length-1]); // tag number from info
					Color color2 = new Color((Integer.parseInt(shapesData[shapesData.length-3]))); // color from RGB-codes
					boolean isFill2; if(shapesData[shapesData.length-2].equals("true")){isFill2 = true;} else {isFill2 = false;} // true/false from isFill string
					Polygon_2D polygon = new Polygon_2D(); // new polygon
					for (int j = shapesData.length-4; j > 0; j--) {
						Point_2D point = new Point_2D(shapesData[j]);
						polygon.add(point);
					}
					System.out.println(polygon.get(0).toString());
					GUI_Shape polygonGUI = new GUIShape(polygon, isFill2, color2, tag);
					polygonGUI.setShape(polygon);
					polygonGUI.setColor(color2);
					polygonGUI.setFilled(isFill2);
					polygonGUI.setTag(tag);
					_shapes.add(polygonGUI);
				}
			}
			myReader.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		String ans = "";
		for(int i=0;i<size();i=i+1) {
			ans += get(i).getShape().toString();
		}
		return ans;
	}


}
