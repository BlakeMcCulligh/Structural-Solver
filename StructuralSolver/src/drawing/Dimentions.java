package drawing;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import UI.MainWindow;
import drawingObjects.DrawingObjects;
import drawingObjects.Line;

public class Dimentions {

	public static RightClickListener1 rClick1;

	public static void dimention() {

		if (DrawingObjects.SelectedLine == -1) {

			rClick1 = new RightClickListener1();

			MainWindow.DrawingPanel.addMouseListener(rClick1);

		} else {

			getClick();

		}

	}

	public static void getClick() {
		RightClickListener2 rClick2 = new RightClickListener2();

		MainWindow.DrawingPanel.addMouseListener(rClick2);
	}

	public static void getDist(Point click) {

		// put text box at curser with restriction to double

		// get double when enter clicked
		// pick units

		// convert to mm
		double distance = 0;

		setDimention(click, distance);

	}

	public static void setDimention(Point click, double distance) {

		// find slope of line
		// adjust p2 to be distance away from p1

		// add dimention to array list to be displayed

		// refresh

	}

	public static double distance(Point p1, Point p2, Point c) {
		double distance = 0.0;

		// Calculate the denominator (length of the line segment)
		double denominator = Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));

		// Check if the denominator is zero (to avoid division by zero)
		if (denominator == 0) {
			return Double.MAX_VALUE; // Return a very large value or handle the case as needed.
		}

		// Calculate the numerator using the formula
		double numerator = Math.abs((p2.x - p1.x) * (c.y - p1.y) - (p2.y - p1.y) * (c.x - p1.x));

		// Calculate the distance
		distance = numerator / denominator;

		return distance;
	}

}

class RightClickListener1 extends MouseAdapter {

	public void mousePressed(MouseEvent e) {

		Point click = MouseInfo.getPointerInfo().getLocation();
		if (SwingUtilities.isLeftMouseButton(e) && MainWindow.DrawingPanel.getBounds().contains(click)) {
			SwingUtilities.convertPointFromScreen(click, MainWindow.DrawingPanel);

			for (int i = 0; i < DrawingObjects.Lines.size(); i++) {
				if (Dimentions.distance(DrawingObjects.Lines.get(i).Points[0], DrawingObjects.Lines.get(i).Points[1],
						click) < 10) {
					DrawingObjects.SelectedLine = i;
					MainWindow.DrawingPanel.removeMouseListener(Dimentions.rClick1);
					Dimentions.getClick();
				}
			}

		}

	}
}

class RightClickListener2 extends MouseAdapter {

	public void mousePressed(MouseEvent e) {
		Point click = MouseInfo.getPointerInfo().getLocation();
		if (SwingUtilities.isLeftMouseButton(e) && MainWindow.DrawingPanel.getBounds().contains(click)) {
			SwingUtilities.convertPointFromScreen(click, MainWindow.DrawingPanel);
			Dimentions.getDist(click);
		}
	}
}
