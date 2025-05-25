package drawingObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * the object for all the lines that are drawn
 * 
 * @author blake M.
 * 
 * @version 1.0 May 21, 2025
 */
public class Line {

	/**
	 * The joints at each end of the line
	 */
	public Joint p[] = new Joint[2];
	
	public boolean selected = false;

	/**
	 * Constructor
	 * 
	 * @param p1 First Joint
	 * @param p2 Second Joint
	 */
	public Line(Joint p1, Joint p2) {
		p[0] = p1;
		p[1] = p2;
	}
	
	public void draw(Graphics2D g) {
		g.setColor(selected ? Color.CYAN : Color.BLACK);
		g.drawLine((int)p[0].x, (int)p[0].y, (int)p[1].x, (int)p[1].y);
	}
	
	public boolean isWithin(Rectangle r) {
		return r.contains(p[0].x, p[0].y) && r.contains(p[1].x, p[1].y);
	}
	
	public boolean touches(Rectangle r) {
		return r.intersectsLine(p[0].x, p[0].y, p[1].x, p[1].y);
	}

}

