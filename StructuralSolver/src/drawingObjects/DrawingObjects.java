package drawingObjects;

import java.util.ArrayList;

/**
 * Holds the array lists of all the difernt drawn in parts
 * 
 * @author blake M.
 * 
 * @version 1.0 May 21, 2025
 */
public class DrawingObjects {

	/**
	 * Array list of all the lines drawn
	 */
	public static ArrayList<Line> Lines = new ArrayList<Line>();

	/**
	 * what line index is curently selected, -1 means no line is seclected
	 */
	public static int SelectedLine = -1;

}
