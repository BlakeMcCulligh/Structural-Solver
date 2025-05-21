package drawing;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import UI.MainWindow;
import drawingObjects.DrawingObjects;
import drawingObjects.Line;
import main.MAIN;

public class DrawLine {

	static Point click1;
	static Point click2;

	static int clickNum = 0;

	public static void drawLine() {

		JPanel DrawingArea = MainWindow.DrawingPanel;

		Point curser = MouseInfo.getPointerInfo().getLocation();
		SwingUtilities.convertPointFromScreen(curser, DrawingArea);

		RightClickListener rClick = new RightClickListener();

		DrawingArea.addMouseListener(rClick);

	}
	
	

	public static void click(MouseEvent e) {

		Point click = MouseInfo.getPointerInfo().getLocation();

		if (SwingUtilities.isLeftMouseButton(e) && MainWindow.DrawingPanel.getBounds().contains(click)) {

			SwingUtilities.convertPointFromScreen(click, MainWindow.DrawingPanel);

			clickNum++;

			if (clickNum == 1) {
				click1 = click;

			}

			if (clickNum == 2) {
				clickNum = 0;
				click2 = click;
				
				click2 = Snaping.snapToAngle(click1, click2);
				
				DrawingObjects.Lines.add(new Line(click1, click2));

				MAIN.print();
			}

		}

	}

}

class RightClickListener extends MouseAdapter {

	public void mousePressed(MouseEvent e) {
		DrawLine.click(e);

	}
}
