package drawing;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import UI.MainWindow;
import drawingObjects.DrawingObjects;
import drawingObjects.Joint;
import drawingObjects.Line;
import main.MAIN;

/**
 * draws a line between the two points cliked
 * 
 * @author Blake M.
 * 
 * @version 1.0 May 20, 2025
 * 
 */
public class DrawLine {

	/**
	 * The two end points
	 */
	static Joint click1, click2;

	/**
	 * How many clicks have been made
	 */
	static int clickNum = 0;

	/**
	 * The main function for drawing a line
	 */
	public static void drawLine() {

		JPanel DrawingArea = MainWindow.DrawingPanel;

		Point curser = MouseInfo.getPointerInfo().getLocation();
		SwingUtilities.convertPointFromScreen(curser, DrawingArea);

		RightClickListener rClick = new RightClickListener();

		DrawingArea.addMouseListener(rClick);

	}

	/**
	 * When there has been a click get its location and determin if a line can now
	 * be drawn or not
	 * 
	 * @param e
	 */
	public static void click(MouseEvent e) {

		Point click = MouseInfo.getPointerInfo().getLocation();

		if (SwingUtilities.isLeftMouseButton(e) && MainWindow.DrawingPanel.getBounds().contains(click)) {

			SwingUtilities.convertPointFromScreen(click, MainWindow.DrawingPanel);

			clickNum++;

			if (clickNum == 1) {
				click1 = new Joint(click.x, click.y);

			}

			if (clickNum == 2) {
				clickNum = 0;
				click2 = new Joint(click.x, click.y);

				click2 = Snaping.snapToAngle(click1, click2);

				DrawingObjects.Lines.add(new Line(click1, click2));

				MAIN.print();
			}

		}

	}

}

/**
 * Lisones for a click on screen
 */
class RightClickListener extends MouseAdapter {

	public void mousePressed(MouseEvent e) {
		DrawLine.click(e);

	}
}
