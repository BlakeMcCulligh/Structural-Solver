package drawingObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Panal {
	
	public ArrayList<Joint> points;
	
	public boolean selected = false;
	public Panal(ArrayList<Joint> points) {
		this.points = points;
	}
	
	public void draw(Graphics2D g) {
		g.setColor(selected ? Color.CYAN : Color.BLACK);
		int[] xPoints = points.stream().mapToInt(p -> (int)p.x).toArray();
		int[] yPoints = points.stream().mapToInt(p -> (int)p.y).toArray();
		g.drawPolygon(xPoints, yPoints, points.size());
	}
	
	public boolean isWithin(Rectangle r) {
		for (Joint p : points) {
			if (!r.contains(p.x, p.y))
				return false;
		}
		return true;
	}
	
	public boolean touches(Rectangle r) {
		Polygon poly = new Polygon();
		for (Joint p : points)
			poly.addPoint((int)p.x, (int)p.y);
		return r.intersects(poly.getBounds());
	}
}

