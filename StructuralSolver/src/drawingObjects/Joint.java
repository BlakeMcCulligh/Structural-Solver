package drawingObjects;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * the object for all the joints on the ends of members and between members
 * 
 * @author blake M.
 * 
 * @version 1.0 May 21, 2025
 */
public class Joint {

	/**
	 * x cordanit
	 */
	public double x;
	/**
	 * y cordanit
	 */
	public double y;
	
	public boolean selected = false;

	/**
	 * Constructor: Creates a joint
	 * 
	 * @param x x cordanit
	 * @param y y cordanit
	 */
	public Joint(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics2D g) {
		g.setColor(selected ? Color.CYAN : Color.BLACK);
		g.fillOval((int)x - 3, (int)y - 3, 6, 6);
	}

}
