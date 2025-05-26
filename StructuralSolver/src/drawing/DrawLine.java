package drawing;

import java.awt.Point;
import UI.MainWindow;
import drawingObjects.Joint;
import drawingObjects.Line;
import drawingObjects.Scene;

/**
 * draws a line between the two points cliked
 * 
 * @author Blake M.
 * 
 * @version 1.0 May 20, 2025
 * 
 */
public class DrawLine implements Runnable {
	@Override
	public void run() {

		while (drawing) {

			Point a = MainWindow.DrawingPanel.getMousePosition();
			if (a != null) {
				int x = (int) a.getX();
				int y = (int) a.getY();
				mouse = new Joint(x, y);
				MainWindow.DrawingPanel.repaint();
			}
			try {
				Thread.currentThread();
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static Joint click1, click2;
	public static Joint mouse;
	public static int clickNum = 0;
	static boolean drawing = false;
	static Thread threaddrawLine;

	public static void drawLine() {
		drawing = true;
		DrawLine drawLine = new DrawLine();
		Thread threaddrawLine = new Thread(drawLine);

		threaddrawLine.start();

	}

	public static void end() {
		click1 = null;
		click2 = null;
		clickNum = 0;
		drawing = false;

	}

	public static void LineFinish(int x1, int y1, int x2, int y2) {
		Joint start = new Joint(x1, y1);
		Joint end = new Joint(x2, y2);

		Line newLine = new Line(start, end);

		Scene.lines.add(newLine);

	}

	public static void point(Joint p) {
		if (clickNum == 0) {
			click1 = p;
			clickNum++;
		} else if (clickNum == 1) {
			click2 = p;
			Line newLine = new Line(click1, click2);
			Scene.lines.add(newLine);
			MainWindow.DrawingPanel.repaint();
			clickNum = 0;
		}

	}

}
