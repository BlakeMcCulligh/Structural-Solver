package drawing;

import drawingObjects.Joint;

/**
 * Snapping for drawing a line
 * 
 * @author Blake M.
 * 
 * @version 1.0 May 20, 2025
 * 
 */
public class Snaping {

	/**
	 * If the line is within a few digress of a right angle or a 45 degree snap to
	 * it.
	 * 
	 * @param mx     X coordinate of the mouse.
	 * @param my     Y coordinate of the mouse.
	 * @param click1 The first point of the line.
	 * @return The new location of the cursor.
	 */
	static Joint snapToAngle(Joint click1, Joint click2) {

		Joint newPoint = click2;

		double xDif = click2.x - click1.x;
		double yDif = click2.y - click1.y;

		double ratio;
		if (yDif == 0) {
			ratio = 0;
		} else {
			ratio = xDif / yDif;
		}

		// snap to x axis
		if (ratio > 5 || ratio < -5) {
			newPoint.x = click2.x;
			newPoint.y = click1.y;

			// snap to y axis
		} else if (ratio < 0.2 && ratio > -0.2) {
			newPoint.x = click1.x;
			newPoint.y = click2.y;

			// snap to 45 (+,-),(-,+) quadrants
		} else if (ratio > 0.9 && ratio < 1.1) {

			newPoint.x = click1.x + xDif;
			newPoint.y = click1.y + xDif;

			// snap to 45 (+,+),(-,-) quadrants
		} else if (ratio < -0.9 && ratio > -1.1) {
			newPoint.x = click1.x + xDif;
			newPoint.y = click1.y - xDif;
		}

		return newPoint;
	}

}
