package drawing;

import java.awt.Point;

public class Snaping {

	/**
	 * If the line is within a few digress of a right angle or a 45 degree snap to
	 * it.
	 * 
	 * @param mx X coordinate of the mouse.
	 * @param my Y coordinate of the mouse.
	 * @param p1 The first point of the line.
	 * @return The new location of the cursor.
	 */
	static Point snapToAngle(Point p1, Point p2) {

		Point newPoint = p2;
		
		int xDif = p2.x-p1.x;
		int yDif = p2.y - p1.y;

		double ratio;
		if (yDif == 0) {
			ratio = 0;
		} else {
			ratio = xDif / yDif;
		}

		// snap to x axis
		if (ratio > 5 || ratio < -5) {
			newPoint.x = p2.x;
			newPoint.y = p1.y;

			// snap to y axis
		} else if (ratio < 0.2 && ratio > -0.2) {
			newPoint.x = p1.x;
			newPoint.y = p2.y;

			// snap to 45 (+,-),(-,+) quadrants
		} else if (ratio > 0.9 && ratio < 1.1) {
			
			
			newPoint.x = p1.x + xDif;
			newPoint.y = p1.y + xDif;

			// snap to 45 (+,+),(-,-) quadrants
		} else if (ratio < -0.9 && ratio > -1.1) {
			newPoint.x = p1.x + xDif;
			newPoint.y = p1.y - xDif;
		}


		return newPoint;
	}
	
}
