package drawingObjects;

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
	public Joint Points[] = new Joint[2];

	/**
	 * Constructor
	 * 
	 * @param p1 First Joint
	 * @param p2 Second Joint
	 */
	public Line(Joint p1, Joint p2) {
		Points[0] = p1;
		Points[1] = p2;
	}

}
